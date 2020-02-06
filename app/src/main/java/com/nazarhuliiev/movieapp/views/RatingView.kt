package com.nazarhuliiev.movieapp.views

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import com.nazarhuliiev.movieapp.R

class RatingView  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val backgroundPaint: Paint = Paint()
    private val borderPaint: Paint = Paint()
    private val ratingPaint: Paint = Paint()
    private val textPaint = Paint()

    private var rectangle: RectF? = null
    private var arcProportion: Float = 0f
    private val maxRating = 10
    var currentRating = 0

    private val animatioDurationMs: Long = 3000

    init {
        backgroundPaint.isAntiAlias = true
        backgroundPaint.color = Color.rgb(230, 238,156)
        backgroundPaint.style = Paint.Style.FILL

        borderPaint.isAntiAlias = true
        borderPaint.color = Color.rgb(92,107,192)
        borderPaint.style = Paint.Style.STROKE
        borderPaint.strokeWidth = context.resources.getDimensionPixelSize(R.dimen.rating_view_border_width).toFloat()

        ratingPaint.isAntiAlias = true
        ratingPaint.color = Color.rgb(105,240,174)
        ratingPaint.style = Paint.Style.STROKE
        ratingPaint.strokeWidth = context.resources.getDimensionPixelSize(R.dimen.rating_view_strip_width).toFloat()

        textPaint.color = Color.rgb(41,121,255)
        textPaint.textSize = context.resources.getDimensionPixelSize(R.dimen.rating_view_text_size).toFloat()
        textPaint.textAlign = Paint.Align.CENTER
    }

    fun setRating(rating: Int) {
        arcProportion = rating * 0.1f
        val currentRatingProportion = currentRating * 0.1f

        ValueAnimator.ofFloat(currentRatingProportion, arcProportion).apply {
            interpolator = DecelerateInterpolator()
            duration = animatioDurationMs
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

        if (rectangle == null) {
            rectangle = RectF(
                0f + borderPaint.strokeWidth,
                0f + borderPaint.strokeWidth,
                width.toFloat() - borderPaint.strokeWidth,
                height.toFloat() * 2 - borderPaint.strokeWidth)
        }

        drawCarcass(canvas)
        drawRatingStrip(canvas)
        drawText(canvas)
    }

    private fun drawCarcass(canvas: Canvas) {
        // Draw background half circle
        canvas.drawArc(rectangle!!, 180f, 180f, false, backgroundPaint)

        // border
        canvas.drawArc(
            rectangle!!.left,
            rectangle!!.top,
            rectangle!!.right,
            rectangle!!.bottom,
            180f,
            180f,
            true,
            borderPaint)

        // inner border
        val innerMargin = borderPaint.strokeWidth + ratingPaint.strokeWidth
        canvas.drawArc(
            rectangle!!.left + innerMargin,
            rectangle!!.top + innerMargin,
            rectangle!!.right - innerMargin,
            rectangle!!.bottom - innerMargin,
            180f,
            180f,
            false,
            borderPaint)
    }

    private fun drawRatingStrip(canvas: Canvas) {
        // Draw rating half circle
        var ratingMargin = ratingPaint.strokeWidth / 2 + borderPaint.strokeWidth / 2
        canvas.drawArc(
            rectangle!!.left + ratingMargin,
            rectangle!!.top + ratingMargin,
            rectangle!!.right - ratingMargin,
            rectangle!!.bottom - ratingMargin,
            180f,
            arcProportion * 180f,
            false,
            ratingPaint)
    }

    private fun drawText(canvas: Canvas) {
        canvas.drawText("$currentRating/$maxRating", width / 2f, height / 2f,textPaint)
    }
}