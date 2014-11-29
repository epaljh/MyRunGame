package com.example.gamedemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	Button btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6
	 ,btn_7,btn_8,btn_9,btn_random,btn_10,btn_11,btn_12,btn_13,btn_14;
	List<Button> btnlist=new ArrayList<Button>();
//	Button [] temp=new Button[13];
	TextView tv_show;
	int count=0;   //当前游戏币数
	Myhandle myhandle=new Myhandle();
	int allposition=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewInit();
	}
	
	public void viewInit()
	{
		    btn_0=(Button)this.findViewById(R.id.btn_0);
		    btn_1=(Button)this.findViewById(R.id.btn_1);
		    btn_2=(Button)this.findViewById(R.id.btn_2);
			btn_3=(Button)this.findViewById(R.id.btn_3);
			btn_4=(Button)this.findViewById(R.id.btn_4);
			btn_5=(Button)this.findViewById(R.id.btn_5);
			btn_6=(Button)this.findViewById(R.id.btn_6);
			btn_7=(Button)this.findViewById(R.id.btn_7);
			btn_8=(Button)this.findViewById(R.id.btn_8);
			btn_9=(Button)this.findViewById(R.id.btn_9);
			btn_10=(Button)this.findViewById(R.id.btn_10);
			btn_11=(Button)this.findViewById(R.id.btn_11);
			btn_12=(Button)this.findViewById(R.id.btn_12);
			btn_13=(Button)this.findViewById(R.id.btn_13);
			btn_14=(Button)this.findViewById(R.id.btn_14);
		    btn_random=(Button)this.findViewById(R.id.btn_random);
		    tv_show=(TextView)this.findViewById(R.id.txt_show);
		    
		    btn_13.setOnClickListener(new OnClick());
		    btn_14.setOnClickListener(new OnClick());
		    btn_random.setOnClickListener(new OnClick());
		    
		     btnlist.add(btn_0);
			 btnlist.add(btn_1);
			 btnlist.add(btn_2);
			 btnlist.add(btn_3);
			 btnlist.add(btn_4);
			 btnlist.add(btn_5);
			 btnlist.add(btn_6);
			 btnlist.add(btn_7);
			 btnlist.add(btn_8);
			 btnlist.add(btn_9);
			 btnlist.add(btn_10);
			 btnlist.add(btn_11);
			 btnlist.add(btn_12);
	}
	
	public class OnClick implements View.OnClickListener
	{
		public void onClick(View v) {
              if(v.getId()==R.id.btn_13)
              {
            	  count+= 1;
            	  tv_show.setText(String.valueOf(count));
              }
              else if(v.getId()==R.id.btn_14)
              {
            	  count=0;
            	  tv_show.setText("0");
              }
              else if(v.getId()==R.id.btn_random)
              { 
            	 if(count==0)
            	 {
            		 Toast.makeText(MainActivity.this, "请投币", Toast.LENGTH_SHORT).show();
            	 }
            	 else
            	 {
            		 count-= 1;
            		  tv_show.setText(String.valueOf(count));
               	      new   MyThread().start();
            	 }
            	
              }
		}
		
	}
	
	public class MyThread extends Thread
	{
		@Override
		public void run() {
			try {
				int temp=8;
				
				while(temp>0)
				{
					Thread.sleep(2000);
					temp--;
					System.out.println(temp);
				    int radom=radom();
				    allposition=radom;
					myhandle.sendEmptyMessage(radom);
				}
				myhandle.sendEmptyMessage(20);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			super.run();
			
		}
	}
	
	public class Myhandle extends Handler
	{
		@Override
		public void handleMessage(Message msg) {
			int position=msg.what;
			if(position!=20)
			{
				Animation shake = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);//加载动画资源文件
				btnlist.get(position).startAnimation(shake); //给组件播放动画效果
			}
			else
			{
				Animation fade_out = AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_out);//加载动画资源文件
				Animation fade_in = AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_in);//加载动画资源文件
				fade_out.setDuration(3000);
//				fade_in.setDuration(3000);
				btnlist.get(allposition).startAnimation(fade_out);
//				btnlist.get(allposition).startAnimation(fade_in);
				
				if(allposition==0)
				{
					 count+=10;
					 tv_show.setText(String.valueOf(count));
				}
				else if(allposition==5)
				{
					count+=5;
					 tv_show.setText(String.valueOf(count));
				}
				else if(allposition==10)
				{
					 count+=1;
					 tv_show.setText(String.valueOf(count));
				}
			}
			super.handleMessage(msg);
		}
	}
	
	public int   radom()
	{
		int result=0;
		Random r = new Random();
		result=r.nextInt(12);
		return result;
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
