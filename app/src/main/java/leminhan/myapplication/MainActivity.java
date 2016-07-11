package leminhan.myapplication;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.dpizarro.uipicker.library.picker.PickerUI;
import com.dpizarro.uipicker.library.picker.PickerUISettings;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private PickerUI mPickerUI;
    private Button btSlide;
    private int currentPosition = -1;
    private List<String> options;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setListeners();

        //Populate list
        options = Arrays.asList(getResources().getStringArray(R.array.countries_array));

        //Populate list
        mPickerUI.setItems(this, options);
        mPickerUI.setColorTextCenter(R.color.background_picker);
        mPickerUI.setColorTextNoCenter(R.color.background_picker);
        mPickerUI.setBackgroundColorPanel(R.color.background_picker);
        mPickerUI.setLinesColor(R.color.background_picker);

        mPickerUI.setOnClickItemPickerUIListener(
                new PickerUI.PickerUIItemClickListener() {

                    @Override
                    public void onItemClickPickerUI(int which, int position, String valueResult) {
                        currentPosition = position;
                        Toast.makeText(MainActivity.this, valueResult, Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void findViews() {
        btSlide = (Button) findViewById(R.id.bt_slide);
        mPickerUI = (PickerUI) findViewById(R.id.picker_ui_view);
    }
    private void setListeners() {
        btSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomColor = -1;

                PickerUISettings pickerUISettings =
                        new PickerUISettings.Builder().withItems(options)
                                .withBackgroundColor(randomColor)
                                .build();

                mPickerUI.setSettings(pickerUISettings);

                if(currentPosition==-1) {
                    mPickerUI.slide();
                }
                else{
                    mPickerUI.slide(currentPosition);
                }
            }
        });
    }
}
