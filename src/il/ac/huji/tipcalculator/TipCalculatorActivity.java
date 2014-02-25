package il.ac.huji.tipcalculator;

import il.ac.huji.tipcalculator.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TipCalculatorActivity extends Activity {
	
	private static double TIP_RATIO = 0.12;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
        Button calculateButton = (Button)findViewById(R.id.btnCalculate);
        
        calculateButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final EditText edtBillAmount = (EditText)findViewById(R.id.edtBillAmount);
				final CheckBox chkRound = (CheckBox)findViewById(R.id.chkRound);
				final Resources resources = getResources();
				
				// Verify that the user inserted a bill amount. If not, show a short toast notifying the user about what happened. 
				if (edtBillAmount.getText().toString().isEmpty()) {
					Toast.makeText(TipCalculatorActivity.this, resources.getString(R.string.toast_empty_bill_amount), Toast.LENGTH_SHORT).show();
					return;
				}
				
				// Calculate and show the proper tip to the user according to his configuration.
				double tip = TIP_RATIO * Double.valueOf(edtBillAmount.getText().toString());
				TextView txtTipResult = (TextView)findViewById(R.id.txtTipResult);
				if (chkRound.isChecked()) {
					txtTipResult.setText(String.format(resources.getString(R.string.text_tip) + " $%d", Math.round(tip)));
				} else {
					txtTipResult.setText(String.format(resources.getString(R.string.text_tip) + " $%.2f", tip));
				}
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tip_calculator, menu);
        return true;
    }
    
}
