package net.studymongolian.fontmetrics;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import static net.studymongolian.fontmetrics.FontMetricsView.DEFAULT_FONT_SIZE_PX;
import static net.studymongolian.fontmetrics.FontMetricsView.DEFAULT_TEXT;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FontMetricsView myFontMetricsView; // custom view
    TextView normalTextView, normalTextView2;
    EditText mTextStringEditText;
    EditText mFontSizeEditText;
    CheckBox cbTop;
    CheckBox cbAscent;
    CheckBox cbBaseline;
    CheckBox cbDescent;
    CheckBox cbBottom;
    CheckBox cbBounds;
    CheckBox cbMeasuredWidth;

    TextView tvTop;
    TextView tvAscent;
    TextView tvBaseline;
    TextView tvDescent;
    TextView tvBottom;
    TextView tvBounds;
    TextView tvMeasuredWidth;
    TextView tvLeading;
    TextView tvAscentDescentHeight, tvTopBottomHeight, tvNormalHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFontMetricsView = findViewById(R.id.viewWindow);
        normalTextView = findViewById(R.id.textView);
        normalTextView2 = findViewById(R.id.textView2);
        mTextStringEditText = findViewById(R.id.etTextString);
        mFontSizeEditText = findViewById(R.id.etFontSize);

        mTextStringEditText.setText(DEFAULT_TEXT);
        mFontSizeEditText.setText(String.valueOf(DEFAULT_FONT_SIZE_PX));
        normalTextView.setText(DEFAULT_TEXT);
        normalTextView2.setText(DEFAULT_TEXT);
        normalTextView.setTextIsSelectable(true);
        normalTextView2.setTextIsSelectable(true);
        normalTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, DEFAULT_FONT_SIZE_PX);
        normalTextView2.setTextSize(TypedValue.COMPLEX_UNIT_PX, DEFAULT_FONT_SIZE_PX);

        findViewById(R.id.updateButton).setOnClickListener(this);
        cbTop = findViewById(R.id.cbTop);
        cbAscent = findViewById(R.id.cbAscent);
        cbBaseline = findViewById(R.id.cbBaseline);
        cbDescent = findViewById(R.id.cbDescent);
        cbBottom = findViewById(R.id.cbBottom);
        cbBounds = findViewById(R.id.cbTextBounds);
        cbMeasuredWidth = findViewById(R.id.cbWidth);

        cbTop.setOnClickListener(this);
        cbAscent.setOnClickListener(this);
        cbBaseline.setOnClickListener(this);
        cbDescent.setOnClickListener(this);
        cbBottom.setOnClickListener(this);
        cbBounds.setOnClickListener(this);
        cbMeasuredWidth.setOnClickListener(this);

        tvTop = findViewById(R.id.tvTop);
        tvAscent = findViewById(R.id.tvAscent);
        tvBaseline = findViewById(R.id.tvBaseline);
        tvDescent = findViewById(R.id.tvDescent);
        tvBottom = findViewById(R.id.tvBottom);
        tvBounds = findViewById(R.id.tvTextBounds);
        tvMeasuredWidth = findViewById(R.id.tvWidth);
        tvLeading = findViewById(R.id.tvLeadingValue);
        tvAscentDescentHeight = findViewById(R.id.tvADHeightValue);
        tvTopBottomHeight = findViewById(R.id.tvTBHeightValue);
        tvNormalHeight = findViewById(R.id.tvNormalHeightValue);
        updateTextViews();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.updateButton:
                String input = mTextStringEditText.getText().toString();
                myFontMetricsView.setText(input);
                normalTextView.setText(input);
                normalTextView2.setText(input);
                int fontSize;
                try {
                    fontSize = Integer.valueOf(mFontSizeEditText.getText().toString());
                } catch (NumberFormatException e) {
                    fontSize = FontMetricsView.DEFAULT_FONT_SIZE_PX;
                }
                myFontMetricsView.setTextSizeInPixels(fontSize);
                normalTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize);
                normalTextView2.setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize);
                updateTextViews();
                hideKeyboard(getCurrentFocus());
                break;
            case R.id.cbTop:
                myFontMetricsView.setTopVisible(cbTop.isChecked());
                break;
            case R.id.cbAscent:
                myFontMetricsView.setAscentVisible(cbAscent.isChecked());
                break;
            case R.id.cbBaseline:
                myFontMetricsView.setBaselineVisible(cbBaseline.isChecked());
                break;
            case R.id.cbDescent:
                myFontMetricsView.setDescentVisible(cbDescent.isChecked());
                break;
            case R.id.cbBottom:
                myFontMetricsView.setBottomVisible(cbBottom.isChecked());
                break;
            case R.id.cbTextBounds:
                myFontMetricsView.setBoundsVisible(cbBounds.isChecked());
                break;
            case R.id.cbWidth:
                myFontMetricsView.setWidthVisible(cbMeasuredWidth.isChecked());
                break;
        }

    }

    public void updateTextViews() {
        float top = myFontMetricsView.getFontMetrics().top;
        float ascent = myFontMetricsView.getFontMetrics().ascent;
        float descent = myFontMetricsView.getFontMetrics().descent;
        float bottom = myFontMetricsView.getFontMetrics().bottom;

        tvTop.setText(String.valueOf(top));
        tvAscent.setText(String.valueOf(ascent));
        tvBaseline.setText(String.valueOf(0f));
        tvDescent.setText(String.valueOf(descent));
        tvBottom.setText(String.valueOf(bottom));
        tvBounds.setText(
                "w: " + myFontMetricsView.getTextBounds().width() +
                        ", h: " + myFontMetricsView.getTextBounds().height()
        );
        tvMeasuredWidth.setText(String.valueOf(myFontMetricsView.getMeasuredTextWidth()));
        tvLeading.setText(String.valueOf(myFontMetricsView.getFontMetrics().leading));
        tvAscentDescentHeight.setText(String.valueOf(descent - ascent));
        tvTopBottomHeight.setText(String.valueOf(bottom - top));
        tvNormalHeight.setText(String.valueOf(normalTextView2.getHeight()));
    }

    private void hideKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
