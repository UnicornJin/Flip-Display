package com.jinyuze.flipdisplay

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.graphics.Paint.FontMetrics
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

class FlipCharView@JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {

    private val TAG = "FlipView"

    private var metrics: FontMetrics
    private var mAnimator: ValueAnimator = ValueAnimator.ofFloat(-0f, -180f)
    private val mMatrix: Matrix = Matrix()
    private var mCamera: Camera = Camera()
    private var mTextBounds: Rect
    private val mWidth = 100f
    private var mOriginX = 0f
    private val mOriginY = 0f
    private val mPadding = 0f
    private val mPanelSpace = 30f
    private val mRadius = 20f
    private val mBackColor = Color.DKGRAY
    private val mFontColor = Color.WHITE
    private val mGap = 3f
    private val mAnimationDuration = 100L
//    private val emptyChar = (0).toChar()
//    private var mDisplayingChar = emptyChar
//    private var mTargetChar = emptyChar
    private var mDisplayingChar = ' '
    private var mTargetChar = ' '

    private val mRectPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.typeface = Typeface.createFromAsset(context.assets, "fonts/Chivo-G.ttf")
        it.textAlign = Paint.Align.CENTER
    }
    private val charList = listOf<Char>(
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        ':', ',', '/', ' '
    )


    init {
        mAnimator.repeatMode = ValueAnimator.RESTART
        mAnimator.interpolator = AccelerateDecelerateInterpolator()
        mAnimator.duration = mAnimationDuration
        mAnimator.addUpdateListener {
            invalidate()
        }
        computeTextSize()
        val nine = "9"
        mTextBounds = Rect()
        mRectPaint.getTextBounds(nine, 0, nine.length, mTextBounds)
        metrics = mRectPaint.fontMetrics
        schedule()
    }

    fun updateContent(targetChar: Char) {
//        if(charList.contains(targetChar)) {
//            mTargetChar = charList[(charList.indexOf(targetChar) - 1 + charList.size) %  charList.size]
//        } else {
//            mTargetChar = targetChar
//        }

        mTargetChar = targetChar
    }

    private fun schedule() {
        post {
            if(mDisplayingChar != mTargetChar){
                mAnimator.resume()
                mAnimator.start()
                if(charList.contains(mDisplayingChar) && charList.contains(mTargetChar)) {
                    mDisplayingChar = charList[(charList.indexOf(mDisplayingChar) + 1) % charList.size]
                    Log.d(TAG, "schedule: mDisplayingChar is: $mDisplayingChar")
                } else {
                    mDisplayingChar = mTargetChar
                }
            }

            postDelayed({ schedule() }, mAnimationDuration)
        }
    }


    private fun computeTextSize() {
        var textSize = 5f
        val bounds = Rect()
        while (true) {
            mRectPaint.textSize = textSize
            mRectPaint.getTextBounds("99", 0, 2, bounds)
            if (bounds.width() <= mWidth - mPadding && bounds.height() <= mWidth - mPadding) {
                textSize += 0.3f
            } else {
                break
            }
        }
        mRectPaint.textSize = textSize
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        if(MeasureSpec.AT_MOST == widthMode || MeasureSpec.AT_MOST == heightMode) {
            setMeasuredDimension(mWidth.toInt(), mWidth.toInt())
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mOriginX = (measuredWidth - mWidth) * 0.5f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawClockItem(
            canvas,
            mOriginX,
            mOriginY,
            mDisplayingChar.toString(),
            if (mDisplayingChar == mTargetChar) {
                mDisplayingChar.toString()
            } else {
                if(charList.contains(mDisplayingChar) && charList.contains(mTargetChar)) {
                    charList[(charList.indexOf(mDisplayingChar) + 1) % charList.size].toString()
                } else {
                    mTargetChar.toString()
                }
            }
        )
    }

    private fun drawClockItem(
        canvas: Canvas?,
        x: Float,
        y: Float,
        curText: String,
        nextText: String
    ) {
        val animate = curText != nextText
        val offsetX = x + mWidth / 2
        val offsetY = y + mWidth / 2 - mTextBounds.centerY()
        // next top part
        canvas?.let {
            it.save()
            it.clipRect(x, y, x + mWidth, y + mWidth / 2 - mGap)
            mRectPaint.color = mBackColor
            it.drawRoundRect(
                x,
                y,
                x + mWidth,
                y + mWidth,
                mRadius,
                mRadius,
                mRectPaint
            )
            mRectPaint.color = mFontColor
            it.drawText(nextText, offsetX, offsetY, mRectPaint)
            it.restore()
        }

        // current bottom part
        canvas?.let {
            it.save()
            mRectPaint.color = mBackColor
            it.clipRect(x, y + mWidth / 2, x + mWidth, y + mWidth)
            it.drawRoundRect(
                x,
                y,
                x + mWidth,
                y + mWidth,
                mRadius,
                mRadius,
                mRectPaint
            )
            mRectPaint.color = mFontColor
            mRectPaint.textAlign
            it.drawText(curText, offsetX, offsetY, mRectPaint)
            it.restore()
        }

        val angle = mAnimator.animatedValue as Float
        canvas?.let {
            it.save()
            mCamera.save()
            if (angle >= -90f) {
                mCamera.rotateX(angle)
            } else {
                mCamera.rotateX(angle + 180f)
            }
            mCamera.getMatrix(mMatrix)
            mMatrix.postTranslate(x + mWidth / 2, y + mWidth / 2)
            mMatrix.preTranslate(-(x + mWidth / 2), -(y + mWidth / 2))
            mCamera.restore()
            if (animate) {
                it.concat(mMatrix)
            }
            if (angle >= -90f) { // current top flipping part
                it.clipRect(x, y, x + mWidth, y + mWidth / 2 - mGap)
                mRectPaint.color = mBackColor
                it.drawRoundRect(
                    x,
                    y,
                    x + mWidth,
                    y + mWidth,
                    mRadius,
                    mRadius,
                    mRectPaint
                )
                mRectPaint.color = mFontColor
                it.drawText(curText, offsetX, offsetY, mRectPaint)
            } else { // next bottom flipping part
                it.clipRect(x, y + mWidth / 2, x + mWidth, y + mWidth)
                mRectPaint.color = mBackColor
                it.drawRoundRect(
                    x,
                    y,
                    x + mWidth,
                    y + mWidth,
                    mRadius,
                    mRadius,
                    mRectPaint
                )
                mRectPaint.color = mFontColor
                it.drawText(nextText, offsetX, offsetY, mRectPaint)
            }
            it.restore()
        }
    }
}