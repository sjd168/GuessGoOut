package org.sujinde.guessgoout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WelcomeToNewYork extends ActionBarActivity {

    final static String FUCK = "WelcomeToNewYork";

    TextView tvResult;
    Button btnGuess;
    Button btnAdd;
    Button btnDelete;
    EditText evAdded;

    ArrayList<String> guessList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initData();
        controlListeners();

    }

    public void controlListeners() {
        tvResult.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                longClickDelete();
                return false;
            }
        });
        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guess();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
    }

    public void guess() {
        int size = guessList.size();
        if (size == 0) {
            Toast.makeText(getApplicationContext(), "当前没有活动，请添加", Toast.LENGTH_SHORT).show();
        } else {
            Random random1 = new Random();
            int randomNum = random1.nextInt() % guessList.size();
            randomNum = Math.abs(randomNum);
            tvResult.setText(guessList.get(randomNum));
        }
    }

    public void add() {
        String addedItem = evAdded.getText().toString().trim();
        if (!TextUtils.isEmpty(addedItem)) {
            guessList.add(addedItem);
            Toast.makeText(getApplicationContext(), "活动添加成功", Toast.LENGTH_SHORT).show();
            evAdded.setText("");
        } else {
            Toast.makeText(getApplicationContext(), "活动怎么能为空呢，BUG", Toast.LENGTH_SHORT).show();
        }
    }

    public void delete() {
        final String result = tvResult.getText().toString();
        if (!TextUtils.isEmpty(result) && guessList.contains(result)) {
            AlertDialog alertDialog = null;
            alertDialog = new AlertDialog.Builder(WelcomeToNewYork.this)

                    .setMessage("确定删除当前活动吗？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            guessList.remove(result);
                            Toast.makeText(getApplicationContext(), "当前活动删除成功", Toast.LENGTH_SHORT).show();
                            tvResult.setText("等待猜一猜");
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create();
            alertDialog.show();

        }
    }

    public void longClickDelete() {
        String[] data = new String[]{};
        data = (String[]) guessList.toArray(data);
        AlertDialog alertDialog = new AlertDialog.Builder(WelcomeToNewYork.this)
                .setTitle("选择删除")
                .setItems(data, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(FUCK, "which :" + which);
                        deleteThird(which);
                        tvResult.setText("等待猜一猜");
                    }
                })
                .create();
        alertDialog.show();
    }

    public void deleteThird(int index) {
        if (index < guessList.size()) {
            guessList.remove(index);
        }
    }

    public void initViews() {
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnGuess = (Button) findViewById(R.id.btnGuess);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        evAdded = (EditText) findViewById(R.id.evAdded);
    }

    public void initData() {

        String[] data = {"看电影", "打牌", "打麻将", "逛街", "打篮球", "饮奶茶", "去市中心", "游泳"};
        guessList = new ArrayList<String>();
        guessList.add("看电影");
        guessList.add("打牌");
        guessList.add("打麻将");
        guessList.add("逛街");
        guessList.add("打篮球");
        guessList.add("饮奶茶");
        guessList.add("去市中心");
        guessList.add("逛街");
        guessList.add("游泳");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        if (id==R.id.orderFood)
        {
            Intent intent=new Intent(this,OrderFoodActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
