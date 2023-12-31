package com.example.emotionalfacecustomview.customViews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.emotionalfacecustomview.R


class PercentagesView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    companion object {
        private const val DEFAULT_BAR_COLOR = Color.BLUE
        private const val DEFAULT_BAR_PERCENTAGE_COLOR = Color.GREEN
        private const val DEFAULT_BORDER_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_WIDTH = 0f
        private const val DEFAULT_PERCENTAGE = 0f
        private const val DEFAULT_BAR_HEIGHT = 200f
        private const val DEFAULT_BAR_WIDTH = 60f
        private const val DEFAULT_PADDING = 0f
        private const val TEXT = ""
        private const val TEXT_SIZE = 16f
        private const val TEXT_COLOR = Color.BLACK
        private const val TEXT_STYLE = Typeface.NORMAL

    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var height = DEFAULT_BAR_HEIGHT
    private var width = DEFAULT_BAR_WIDTH
    private var barColor = DEFAULT_BAR_COLOR
    private var borderColor = DEFAULT_BORDER_COLOR
    private var percentageColor = DEFAULT_BAR_PERCENTAGE_COLOR
    private var text = TEXT
    private var textSize = TEXT_SIZE
    private var textColor = TEXT_COLOR
    private var textStyle = TEXT_STYLE


    // var percentage = DEFAULT_PERCENTAGE
    private var borderWidth = DEFAULT_BORDER_WIDTH
    private var padding = DEFAULT_PADDING

    var percentage = DEFAULT_PERCENTAGE
        set(value) {
            field = value
            invalidate()
        }

    var labelText: String
        get() = text
        set(value) {
            text = value
            invalidate()
        }

    private var labelTextSize: Float
        get() = textSize
        set(value) {
            textSize = value
            textPaint.textSize = value
            invalidate()
        }

    var labelTextColor: Int
        get() = textColor
        set(value) {
            textColor = value
            textPaint.color = value
            invalidate()
        }

    var labelTextStyle: Int
        get() = textStyle
        set(value) {
            textStyle = value
            textPaint.typeface = Typeface.defaultFromStyle(value)
            invalidate()
        }

    init {
        paint.isAntiAlias = true // it is use for smoothing the paint
        setupAttributes(attributeSet)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // Set the measured dimensions based on your custom view's requirements
        val widthSize = resolveSize(
            (width * resources.displayMetrics.density).toInt(),
            widthMeasureSpec
        )
        val heightSize = resolveSize(
            (height * resources.displayMetrics.density).toInt(),
            heightMeasureSpec
        )
        setMeasuredDimension(widthSize, heightSize)
    }

    override fun onDraw(canvas: Canvas) {
        Log.d("OnDrawCalled", "called")
        super.onDraw(canvas)
        drawBarBackground(canvas)
        drawText(canvas)
    }

    private fun drawBarBackground(canvas: Canvas?) {

        val mainPercentage = (1 - (percentage / 100f))

        paint.color = barColor
        paint.style = Paint.Style.FILL

        canvas?.drawRect(
            padding,
            padding,
            (canvas.width - padding),
            (canvas.height - padding),
            paint
        )




        paint.color = percentageColor
        paint.style = Paint.Style.FILL


        canvas?.drawRect(
            padding + (borderWidth / 2),
            padding + ((canvas.height * mainPercentage) + (borderWidth / 2)),
            (canvas.width - (padding + borderWidth / 2)),
            (canvas.height - (padding + borderWidth / 2)),
            paint
        )

        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth


        canvas?.drawRect(
            padding + (borderWidth),
            padding + (borderWidth /2),
            (canvas.width - (padding + borderWidth )),
            (canvas.height - (padding + borderWidth/2 )),
            paint
        )

    }

    private fun setupAttributes(attrs: AttributeSet) {
        val typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.PercentagesView, 0, 0)

        borderColor = typedArray.getColor(
            R.styleable.PercentagesView_barBorderColor,
            DEFAULT_BORDER_COLOR
        )

        barColor = typedArray.getColor(
            R.styleable.PercentagesView_barColor,
            DEFAULT_BAR_COLOR
        )

        borderWidth = typedArray.getDimension(
            R.styleable.PercentagesView_barBorderWidth,
            DEFAULT_BORDER_WIDTH
        )
        labelText = typedArray.getString(R.styleable.PercentagesView_labelText) ?: text
        labelTextSize = typedArray.getDimension(
            R.styleable.PercentagesView_labelTextSize,
            14f
        )
        labelTextColor = typedArray.getColor(
            R.styleable.PercentagesView_labelTextColor,
            Color.BLACK
        )
        labelTextStyle = typedArray.getInt(
            R.styleable.PercentagesView_labelTextStyle,
            Typeface.NORMAL
        )
        typedArray.recycle()//it release the memory uses to prevent from memory leak
    }

    private fun drawText(canvas: Canvas) {
        textPaint.color = textColor
        textPaint.textSize = textSize
        textPaint.typeface = Typeface.defaultFromStyle(textStyle)
        textPaint.textAlign = Paint.Align.CENTER

        val x = (((canvas.width / 2)+(padding + borderWidth/2 )).toFloat())
        val y = ((canvas.height / 2)+(padding + borderWidth/2 )).toFloat()

        canvas.drawText(text, x, y, textPaint)
    }

}

