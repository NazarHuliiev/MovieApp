package com.nazarhuliiev.movieapp.views

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.core.content.ContextCompat
import com.nazarhuliiev.movieapp.R

class RatingView  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val backgroundPaint: Paint = Paint().apply {
        isAntiAlias = true
        color = ContextCompat.getColor(context, R.color.rating_view_background)
        style = Paint.Style.FILL
    }

    private val borderPaint: Paint = Paint().apply {
        isAntiAlias = true
        color = ContextCompat.getColor(context, R.color.rating_view_border)
        style = Paint.Style.STROKE
        strokeWidth = context.resources.getDimensionPixelSize(R.dimen.rating_view_border_width).toFloat()
    }

    private val ratingPaint: Paint = Paint().apply {
        isAntiAlias = true
        color = ContextCompat.getColor(context, R.color.rating_view_rating)
        style = Paint.Style.STROKE
        strokeWidth = context.resources.getDimensionPixelSize(R.dimen.rating_view_strip_width).toFloat()
    }

    private val textPaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.rating_view_text_color)
        textSize = context.resources.getDimensionPixelSize(R.dimen.rating_view_text_size).toFloat()
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }

    private var rectangle = RectF()
    private var arcProportion: Float = 0f
    private val maxRating = 10
    private var currentRating = 0f

    private val animationDurationMs: Long = 3000

    fun setRating(rating: Float) {
        arcProportion = rating * 0.1f
        val currentRatingProportion = currentRating * 0.1f

        ValueAnimator.ofFloat(currentRatingProportion, arcProportion).apply {
            interpolator = DecelerateInterpolator()
            duration = animationDurationMs
            addUpdateListener { animator ->
                this@RatingView.arcProportion = animator.animatedValue as Float
                this@RatingView.invalidate()
            }
            start()
        }
        currentRating = rating
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        rectangle.set(
            0 + borderPaint.strokeWidth,
            0 + borderPaint.strokeWidth,
            width.toFloat() - borderPaint.strokeWidth,
            height.toFloat() * 2 - borderPaint.strokeWidth)

        drawCarcass(canvas)
        drawRatingStrip(canvas)
        drawText(canvas)
    }

    private fun drawCarcass(canvas: Canvas) {
        // Draw background half circle
        canvas.drawArc(rectangle, 180f, 180f, false, backgroundPaint)

        // border
        canvas.drawArc(
            rectangle.left,
            rectangle.top,
            rectangle.right,
            rectangle.bottom,
            180f,
            180f,
            true,
            borderPaint)

        // inner border
        val innerMargin = borderPaint.strokeWidth + ratingPaint.strokeWidth
        canvas.drawArc(
            rectangle.left + innerMargin,
            rectangle.top + innerMargin,
            rectangle.right - innerMargin,
            rectangle.bottom - innerMargin,
            180f,
            180f,
            false,
            borderPaint)
    }

    private fun drawRatingStrip(canvas: Canvas) {
        // Draw rating half circle
        var ratingMargin = ratingPaint.strokeWidth / 2 + borderPaint.strokeWidth / 2
        canvas.drawArc(
            rectangle.left + ratingMargin,
            rectangle.top + ratingMargin,
            rectangle.right - ratingMargin,
            rectangle.bottom - ratingMargin,
            180f,
            arcProportion * 180f,
            false,
            ratingPaint)
    }

    private fun drawText(canvas: Canvas) {
        val ratingMargin = ratingPaint.strokeWidth / 2 + borderPaint.strokeWidth / 2
        val yPosition = (height / 2f) + rectangle.top + ratingMargin + textPaint.textSize / 2
        canvas.drawText("$currentRating/$maxRating", width / 2f, yPosition, textPaint)
    }
}