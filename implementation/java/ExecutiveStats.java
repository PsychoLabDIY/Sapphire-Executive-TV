package com.gathin.tvhome;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.ReplacementSpan;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Adds one tiny native progress indicator to the already refreshed stat line. */
final class ExecutiveStats {
    private static final Pattern RAM = Pattern.compile("(?i)(\\d+)\\s*/\\s*(\\d+)\\s*MB");
    private static final int GOLD = 0xFFD6B879;
    private ExecutiveStats() {}
    static CharSequence decorate(CharSequence base, String raw, String weather) {
        SpannableStringBuilder text = new SpannableStringBuilder(base);
        Matcher m = RAM.matcher(raw == null ? "" : raw);
        if (m.find()) {
            try {
                double used = Double.parseDouble(m.group(1)), total = Double.parseDouble(m.group(2));
                if (total > 0) {
                    int start = text.length();
                    text.append("  \uFFFC");
                    text.setSpan(new Bar((float)Math.max(0, Math.min(1, used / total))), start + 2, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            } catch (NumberFormatException ignored) {}
        }
        if (weather != null && !weather.isEmpty()) {
            text.append("   \u2022   ");
            int start = text.length();
            text.append(weather);
            int temperature = weather.lastIndexOf(' ');
            if (temperature >= 0 && temperature < weather.length() - 1) text.setSpan(new ForegroundColorSpan(GOLD), start + temperature + 1, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return text;
    }
    private static final class Bar extends ReplacementSpan {
        private final float fraction;
        Bar(float fraction) { this.fraction = fraction; }
        @Override public int getSize(Paint paint, CharSequence t, int start, int end, Paint.FontMetricsInt fm) {
            return Math.round(paint.getTextSize() * 2.6f);
        }
        @Override public void draw(Canvas canvas, CharSequence t, int start, int end, float x, int top, int y, int bottom, Paint paint) {
            int color = paint.getColor(); Paint.Style style = paint.getStyle();
            float w = getSize(paint, t, start, end, null), h = Math.max(2f, paint.getTextSize() * .15f), cy = y + paint.ascent() * .35f;
            paint.setStyle(Paint.Style.FILL); paint.setColor(0x80525D68);
            canvas.drawRoundRect(x, cy-h/2, x+w, cy+h/2, h/2, h/2, paint);
            paint.setColor(GOLD);
            canvas.drawRoundRect(x, cy-h/2, x+w*fraction, cy+h/2, h/2, h/2, paint);
            paint.setColor(color); paint.setStyle(style);
        }
    }
}
