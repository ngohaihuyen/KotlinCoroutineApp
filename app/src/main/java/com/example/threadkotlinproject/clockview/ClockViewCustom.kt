package com.example.coroutineproject.clockview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.threadkotlinproject.Utils

class ClockViewCustom : View {
    private var w = 0
    private var h = 0
    private lateinit var normalPaint: Paint
    private lateinit var specialPaint: Paint
    private lateinit var circlePaint: Paint
    private lateinit var handPaint: Paint
    private lateinit var hourhandPaint: Paint
    private lateinit var iconPaint: Paint
    private lateinit var textPaint: Paint
    private val TOTAL_ANGLE = 360
    private val dotCount = 12
    private var secondDegree = 0.0
    private var minuteDegree = 0.0
    private var hourDegree = 0.0
    private var weightPoint = 0f
    private var specialWeightPoint = 0f
    private var isNumber = false

    constructor(context:Context) : super(context) {
        init(context)
    }
    constructor(context:Context, attrs: AttributeSet?): super(context,attrs) {
        init(context)
    }

    constructor(context:Context, attrs:AttributeSet?, defStyleAttr:Int): super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context:Context) {
        initPaints()
        weightPoint = Utils.dpToPx(context, 10f)
        specialWeightPoint = Utils.dpToPx(context, 15f)
    }

    private fun initPaints() {
        normalPaint = Paint().apply {
            color = Color.parseColor("#C13636")
            style = Paint.Style.FILL
            isAntiAlias = true
            strokeWidth = Utils.dpToPx(context, 2f)
        }

        specialPaint = Paint().apply {
            color = Color.parseColor("#C13636")
            style = Paint.Style.FILL
            isAntiAlias = true
            strokeWidth = Utils.dpToPx(context, 5f)
        }

        circlePaint = Paint().apply {
            color = Color.parseColor("#FFFFFFFF")
            style = Paint.Style.FILL
            isAntiAlias = true
            strokeWidth = Utils.dpToPx(context, 6f)
        }

        hourhandPaint = Paint().apply {
            color = Color.parseColor("#697AD8")
            style = Paint.Style.STROKE
            isAntiAlias = true
        }

        handPaint = Paint().apply {
            color = Color.parseColor("#FF000000")
            style = Paint.Style.STROKE
            isAntiAlias = true
        }

        iconPaint = Paint().apply {
            color = Color.parseColor("#FF000000")
            style = Paint.Style.FILL
            isAntiAlias = true
        }

        textPaint = Paint().apply {
            color = Color.parseColor("#697AD8")
            style = Paint.Style.FILL
            isAntiAlias = true
            textSize = Utils.dpToPx(context, 12f)
        }
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        this.w = w
        this.h = h
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (w == 0 || h == 0) return

        drawCircle(canvas)
        drawPoints(canvas)
        drawHands(canvas)
        drawCenterIcon(canvas)
    }

    private fun drawCircle(canvas: Canvas) {
        canvas.drawCircle(w / 2f, h / 2f, w / 2f - Utils.dpToPx(context, 3f), circlePaint)
    }

    private fun drawPoints(canvas: Canvas) {
        for (i in 0 until dotCount) {
            val longPoint: Float
            val mPaint: Paint
            if (i % 3 == 0) { // xác định xem có phải các điểm đặc biệt 3, 6, 9, 12
                longPoint = specialWeightPoint
                mPaint = specialPaint
            } else {
                longPoint = weightPoint
                mPaint = normalPaint
            }

            val degree = Math.toRadians((TOTAL_ANGLE.toDouble() / dotCount) * i)

            val sinAngle = Math.sin(degree).toFloat()
            val cosAngle = Math.cos(degree).toFloat()

            val startX = (sinAngle * (w / 2f - longPoint)) + w / 2f
            val startY = (h / 2f) - (cosAngle * (h / 2f - longPoint))

            var padding = 0
            if (isNumber) {
                padding = 40
            }
            val stopX = (sinAngle * (w / 2f - padding)) + w / 2f
            val stopY = (h / 2f) - (cosAngle * (h / 2f - padding))

            if (isNumber) {
                val value = if (i == 0) {
                    Utils.toRoman(12)
                } else {
                    Utils.toRoman(i)
                }
                canvas.drawText(value, stopX, stopY, textPaint)
            } else {
                canvas.drawLine(startX, startY, stopX, stopY, mPaint)
            }
        }
    }

    private fun drawItem(canvas: Canvas, degree: Double, length: Float, width: Float, paint: Paint) {
        paint.strokeWidth = width
        val centerX = w / 2f
        val centerY = h / 2f
        val endX = centerX + (length * Math.sin(degree)).toFloat()
        val endY = centerY - (length * Math.cos(degree)).toFloat()
        canvas.drawLine(centerX, centerY, endX, endY, paint)
    }

    private fun drawHands(canvas: Canvas) {
        drawItem(canvas, secondDegree, w / 2f - 30f, 3f, handPaint)
        drawItem(canvas, minuteDegree, w / 2f - 40f, 6f, handPaint)
        drawItem(canvas, hourDegree, w / 2f - 50f, 12f, hourhandPaint)
    }

    private fun drawCenterIcon(canvas: Canvas) {
        val centerX = w / 2f
        val centerY = h / 2f
        val radius = Utils.dpToPx(context, 10f)
        canvas.drawCircle(centerX, centerY, radius, iconPaint)
    }

    fun setTime(hour: Int, minute: Int, second: Int) {
        secondDegree = Math.toRadians((second / 60.0) * 360)
        minuteDegree = Math.toRadians((minute / 60.0) * 360 + (second / 60.0) * 6)
        hourDegree = Math.toRadians(((hour % 12) / 12.0) * 360 + (minute / 60.0) * 30 + (second / 3600.0) * 30)
        invalidate()
    }

    fun setIsNumber(isNumber: Boolean) {
        if (this.isNumber != isNumber) {
            this.isNumber = isNumber
            invalidate()
        }
    }
}