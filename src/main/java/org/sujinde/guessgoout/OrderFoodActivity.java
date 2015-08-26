package org.sujinde.guessgoout;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


public class OrderFoodActivity extends ActionBarActivity {

    TextView tvOrder;
    Button btnGuess;

    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);
        initViews();
        initData();
        controlListeners();
    }

    void initViews() {
        tvOrder = (TextView) findViewById(R.id.tvOrder);
        btnGuess = (Button) findViewById(R.id.btnGuess);
    }

    void initData() {
        dataList = new ArrayList<String>();
        dataList.add("猪肉");
        dataList.add("豆芽");
        dataList.add("肉丸");
        dataList.add("鸡肉");
        dataList.add("烧鸭");
        dataList.add("西北风");
        dataList.add("生菜");
    }

    void controlListeners() {
        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFood();
            }
        });
    }

    public int generateRandomNum() {
        Random random = new Random();
        if (0 == dataList.size())
            return -1;
        return Math.abs(random.nextInt() % dataList.size());

    }

    public void addFood() {
        String orderedFood = "";
        if (-1 != generateRandomNum()) {
            int foodNum=generateRandomNum();
            orderedFood = dataList.get(foodNum);
            tvOrder.append(orderedFood+"    ");
            dataList.remove(foodNum);
        } else {
            Toast.makeText(getApplicationContext(), "全部原料已经添加", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order_food, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }
}
