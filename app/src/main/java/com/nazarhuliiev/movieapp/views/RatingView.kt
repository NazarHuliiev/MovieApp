package com.nazarhuliiev.movieapp.views

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import com.nazarhuliiev.movieapp.views.extensions.dpToPx

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

    private val animationBaseDurationMs: Long = 3000

    init {
        backgroundPaint.isAntiAlias = true
        backgroundPaint.color = Color.rgb(230, 238,156)
        backgroundPaint.style = Paint.Style.FILL

        borderPaint.isAntiAlias = true
        borderPaint.color = Color.rgb(92,107,192)
        borderPaint.style = Paint.Style.STROKE
        borderPaint.strokeWidth = 1.dpToPx()

        ratingPaint.isAntiAlias = true
        ratingPaint.color = Color.rgb(41,121,255)
        ratingPaint.style = Paint.Style.STROKE
        ratingPaint.strokeWidth = 12.dpToPx()

        textPaint.color = Color.rgb(41,121,255)
        textPaint.textSize = 25.dpToPx()
        textPaint.textAlign = Paint.Align.CENTER
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawARGB(80,102,204,255)

        if (rectangle == null) {
            rectangle = RectF(0f, 0f, width.toFloat(), height.toFloat() * 2)
        }

        // Draw background half circle
        canvas.drawArc(rectangle!!, 180f, 180f, false, backgroundPaint)

        // border
        val externalMargin = borderPaint.strokeWidth
        canvas.drawArc(
            rectangle!!.left + externalMargin,
            rectangle!!.top + externalMargin,
            rectangle!!.right - externalMargin,
            rectangle!!.bottom - externalMargin * 2,
            180f,
            180f,
            true,
            borderPaint)

        // inner border
        val innerMargin = borderPaint.strokeWidth * 2 + ratingPaint.strokeWidth
        canvas.drawArc(
            rectangle!!.left + innerMargin,
            rectangle!!.top + innerMargin,
            rectangle!!.right - innerMargin,
            rectangle!!.bottom - innerMargin,
            180f,
            180f,
            false,
            borderPaint)

        // Draw rating half circle
        var ratingMargin = 7.dpToPx() //????
        canvas.drawArc(
            rectangle!!.left + ratingMargin,
            rectangle!!.top + ratingMargin,
            rectangle!!.right - ratingMargin,
            rectangle!!.bottom - ratingMargin,
            180f,
            arcProportion * 180f,
            false,
            ratingPaint)

        canvas.drawText("$currentRating/$maxRating", width / 2f, height / 2f,textPaint)
    }

    fun setRating(rating: Int) {
        currentRating = rating
        arcProportion = rating * 0.1f

        ValueAnimator.ofFloat(0f, arcProportion).apply {
            interpolator = DecelerateInterpolator()
            duration = animationBaseDurationMs
            addUpdateListener { animator ->
                this@RatingView.arcProportion = animator.animatedValue as Float
                this@RatingView.invalidate()
            }
            start()
        }
    }
}