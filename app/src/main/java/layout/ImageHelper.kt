package layout

import android.content.Context
import android.graphics.*

object ImageHelper {
    fun getRoundedCornerBitmap(context: Context, input: Bitmap?, pixels: Int, w: Int, h: Int, squareTL: Boolean, squareTR: Boolean, squareBL: Boolean, squareBR: Boolean): Bitmap {
        val output = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val densityMultiplier = context.resources.displayMetrics.density
        val color = -0xbdbdbe
        val paint = Paint()
        val rect = Rect(0, 0, w, h)
        val rectF = RectF(rect)
        //make sure that our rounded corner is scaled appropriately
        val roundPx = pixels * densityMultiplier
        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = color
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint)
        //draw rectangles over the corners we want to be square
        if (squareTL) {
            canvas.drawRect(0f, h / 2.toFloat(), w / 2.toFloat(), h.toFloat(), paint)
        }
        if (squareTR) {
            canvas.drawRect(w / 2.toFloat(), h / 2.toFloat(), w.toFloat(), h.toFloat(), paint)
        }
        if (squareBL) {
            canvas.drawRect(0f, 0f, w / 2.toFloat(), h / 2.toFloat(), paint)
        }
        if (squareBR) {
            canvas.drawRect(w / 2.toFloat(), 0f, w.toFloat(), h / 2.toFloat(), paint)
        }
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(input, 0f, 0f, paint)
        return output
    }

    fun getRoundedCornerBitmap1(bitmap: Bitmap, pixels: Int): Bitmap {
        val output = Bitmap.createBitmap(bitmap.width, bitmap
                .height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val color = -0xbdbdbe
        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        val rectF = RectF(rect)
        val roundPx = pixels.toFloat()
        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = color
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)
        return output
    }
}