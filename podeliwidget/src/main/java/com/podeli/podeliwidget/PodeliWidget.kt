package com.podeli.podeliwidget

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.podeli.podeliwidget.FormatUtils.formatDate
import com.podeli.podeliwidget.FormatUtils.formatMoney
import kotlinx.datetime.Clock
import kotlin.math.ceil

class PodeliWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val paymentAmountTextView: TextView
    private val partPaymentContainer: LinearLayout

    init {
        View.inflate(context, R.layout.widget_podeli, this)

        paymentAmountTextView = findViewById(R.id.payment_amount_text_view)
        partPaymentContainer = findViewById(R.id.progress_bar_container)

        setBackgroundResource(R.drawable.bg_widget)
    }

    private fun init(paymentAmount: Double?) {
        val screenWidth = resources.displayMetrics.widthPixels
        var countLastAmount = 0.0
        DatesGenerator.dates.mapIndexed { index, date ->
            val view = View(context)

            val layoutParams = LayoutParams(
                screenWidth / 4 - 54,
                resources.getDimension(R.dimen.progress_bar_height).toInt()
            )

            layoutParams.setMargins(
                0,
                0,
                resources.getDimension(R.dimen.progress_bar_right_margin).toInt(),
                resources.getDimension(R.dimen.progress_bar_bottom_margin).toInt(),
            )
            view.layoutParams = layoutParams
            view.setBackgroundResource(
                if (date.epochSeconds == Clock.System.now().epochSeconds) {
                    R.drawable.ic_progress_bar_active
                } else {
                    R.drawable.ic_progress_bar
                }
            )
            val dateTextView = TextView(context)
            dateTextView.typeface = Typeface.create(
                ResourcesCompat.getFont(context, R.font.styrene_b_lc_regular),
                Typeface.NORMAL
            )
            dateTextView.setTextColor(ContextCompat.getColor(context, R.color.date_text_color))
            dateTextView.textSize = 10f
            dateTextView.text = if (date.epochSeconds == Clock.System.now().epochSeconds) {
                resources.getString(R.string.today)
            } else {
                context.formatDate(date)
            }
            val amountTextView = TextView(context)
            amountTextView.typeface = Typeface.create(
                ResourcesCompat.getFont(context, R.font.styrene_b_lc_medium),
                Typeface.NORMAL
            )
            amountTextView.setTextColor(ContextCompat.getColor(context, R.color.amount_text_color))
            amountTextView.textSize = 11f
            amountTextView.text =
                if (paymentAmount != null) {
                    if (index != 3) {
                        countLastAmount += ceil(paymentAmount / 4)
                        context.formatMoney(ceil(paymentAmount / 4))
                    } else {
                        context.formatMoney(paymentAmount - countLastAmount)
                    }
                } else {
                    "25%"
                }

            val partPaymentItem = LinearLayout(context)
            partPaymentItem.layoutParams =
                LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            partPaymentItem.orientation = LinearLayout.VERTICAL

            partPaymentItem.addView(view)
            partPaymentItem.addView(dateTextView)
            partPaymentItem.addView(amountTextView)

            partPaymentContainer.addView(partPaymentItem)
        }
    }

    fun setPaymentAmount(paymentAmount: Double? = null) {
        paymentAmountTextView.text =
            if (paymentAmount != null) context.formatMoney(paymentAmount) else "100%"
        init(paymentAmount)
    }
}