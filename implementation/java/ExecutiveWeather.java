package com.gathin.tvhome;

import android.os.SystemClock;
import org.json.JSONObject;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/** Foreground-triggered, fixed-city weather; no GPS, service, alarm or persistent tracking. */
final class ExecutiveWeather {
    private static final long REFRESH = 30 * 60 * 1000L;
    private static final long RETRY = 5 * 60 * 1000L;
    private static volatile long nextAttempt, lastSuccess;
    private static volatile String cached = "";
    private static boolean busy;
    private ExecutiveWeather() {}

    static synchronized String current() {
        long now = SystemClock.elapsedRealtime();
        if (!busy && now >= nextAttempt) {
            busy = true;
            nextAttempt = now + RETRY;
            Thread worker = new Thread(ExecutiveWeather::fetch, "executive-weather");
            worker.setDaemon(true);
            worker.start();
        }
        return lastSuccess > 0 && now - lastSuccess < 2 * 60 * 60 * 1000L ? cached : "";
    }

    private static void fetch() {
        HttpURLConnection connection = null;
        try {
            // City centre, not the TV/user location. Open-Meteo public non-commercial API.
            URL url = new URL("https://api.open-meteo.com/v1/forecast?latitude=50.72&longitude=23.25&current=temperature_2m,weather_code&timezone=Europe%2FWarsaw");
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(4000);
            connection.setReadTimeout(4000);
            connection.setInstanceFollowRedirects(false);
            if (connection.getResponseCode() != 200) return;
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            try (InputStream in = connection.getInputStream()) {
                byte[] buf = new byte[2048]; int n;
                while ((n = in.read(buf)) != -1) {
                    if (bytes.size() + n > 32768) return;
                    bytes.write(buf, 0, n);
                }
            }
            JSONObject current = new JSONObject(bytes.toString("UTF-8")).getJSONObject("current");
            double temp = current.getDouble("temperature_2m");
            if (Double.isNaN(temp) || Double.isInfinite(temp) || temp < -80 || temp > 65) return;
            int code = current.getInt("weather_code");
            String icon = code == 0 ? "\u2600" : code <= 3 ? "\u2601" : code >= 71 && code <= 77 || code == 85 || code == 86 ? "\u2744" : code >= 95 ? "\u26A1" : code >= 51 ? "\u2614" : "\u2601";
            cached = "Zamo\u015b\u0107  " + icon + " " + Math.round(temp) + "\u00b0C";
            lastSuccess = SystemClock.elapsedRealtime();
            nextAttempt = lastSuccess + REFRESH;
        } catch (Exception ignored) { /* Existing launcher remains usable offline. */ }
        finally {
            if (connection != null) connection.disconnect();
            synchronized (ExecutiveWeather.class) { busy = false; }
        }
    }
}
