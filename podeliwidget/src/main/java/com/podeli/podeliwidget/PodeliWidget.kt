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
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class PodeliWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val paymentAmountTextView: TextView
    private val partPaymentContainer: LinearLayout
    private val licenceAgreementTextView: TextView

    private var licenceAgreementListener: () -> Unit = {}

    init {
        View.inflate(context, R.layout.widget_podeli, this)

        paymentAmountTextView = findViewById(R.id.payment_amount_text_view)
        partPaymentContainer = findViewById(R.id.progress_bar_container)
        licenceAgreementTextView = findViewById(R.id.licence_agreement_text_view)

        licenceAgreementTextView.setOnClickListener {
            licenceAgreementListener.invoke()
        }

        setBackgroundResource(R.drawable.bg_widget)
    }

    private fun init(paymentAmount: Double?) {
        val screenWidth = resources.displayMetrics.widthPixels
        var countLastAmount = 0.0
        var partAmount = 0.0

        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.UP
        df.decimalFormatSymbols = DecimalFormatSymbols.getInstance(Locale.ENGLISH)
        paymentAmount?.let {
            partAmount = df.format(it / 4).toDouble()
            countLastAmount = it - (partAmount * 3)
        }

        partPaymentContainer.removeAllViews()
        partPaymentContainer.invalidate()

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
                if (index == 0) {
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
            dateTextView.text = if (index == 0) {
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
                        context.formatMoney(partAmount)
                    } else {
                        context.formatMoney(countLastAmount)
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

    fun setOnLicenceAgreementListener(listener: () -> Unit) {
        licenceAgreementListener = listener
    }
}