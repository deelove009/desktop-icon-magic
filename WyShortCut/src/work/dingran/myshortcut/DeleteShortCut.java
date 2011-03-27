/**
 *
 *@author	dingran
 *创建日期	2010-7-27 上午11:47:52
 *
 */
package work.dingran.myshortcut;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DeleteShortCut extends Activity {

static GridView grid;
	
	static final String TAG = "ShowShortCut";
	
	static final String AUTHORITY = "com.android.launcher.settings";
	
	static final String TABLE_FAVORITES = "favorites";
	
	static final String PARAMETER_NOTIFY = "notify";
	
	private static final String ACTION_UNINSTALL_SHORTCUT = 
		"com.android.launcher.action.UNINSTALL_SHORTCUT";
	
	final Uri shortCutUri = Uri.parse("content://" +
            AUTHORITY + "/" + TABLE_FAVORITES +
            "?" + PARAMETER_NOTIFY + "=true");
	
	public List<String> ids = new ArrayList<String>();
	public List<String> name = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		loadShortCuts();
        
        this.setContentView(R.layout.layout_animation_5);
        grid = (GridView) findViewById(R.id.grid);
        grid.setAdapter(new ShortCutAdapter());
		
	}

	private List<ResolveInfo> mShortCuts = new ArrayList<ResolveInfo>();
    private void loadShortCuts() {
    	
    	ContentResolver cr = this.getContentResolver();
    	
    	
    	 String[] projection = new String[]{
         		"_id",
         		"title",
         		"intent",
         		
         };
    	 
    	 Cursor c = this.managedQuery(shortCutUri, projection, null, null, null);
    	
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        /*ComponentName cn = new ComponentName("com.yarin.android.Examples_06_03",".Activity01");
        mainIntent.setComponent(cn);*/
        
        if(c.moveToFirst()){
        	String id = null;
    		int idColumn  = c.getColumnIndex("_id");
    		String title = null;
    		int titleColumn  = c.getColumnIndex("title");
    		String intent = null;
    		int intentColumn =  c.getColumnIndex("intent");
    		
    		do{
    			id = c.getString(idColumn);
    			if(id!=null){
    				Log.e(TAG, id);
//    				tv.append(id);
    				ids.add(id);
    			}
    			
    			title = c.getString(titleColumn);
    			if(title!=null){
//    				Log.e(TAG, title);
//    				tv.append(title);
    				name.add(title);
    			}
    			
    			intent =  c.getString(intentColumn);
    			if(intent!=null){
    				Log.e(TAG, intent);
//    				tv.append(intent);
    				
    				
    				
    				String packageName = intent.substring(intent.indexOf("component=")+10,intent.indexOf('/'));
    				Log.e(TAG, packageName);
    				String activityName = intent.substring(intent.indexOf("/.")+1,intent.indexOf(";end"));
    				Log.e(TAG, packageName+activityName);
    				
    				ComponentName ct = new ComponentName(packageName,packageName+activityName);
    				
//    				mainIntent.setPackage(packageName);
    				mainIntent.setComponent(ct);
//    				mShortCuts = getPackageManager().queryIntentActivities(mainIntent, 0);
    				mShortCuts.addAll(getPackageManager().queryIntentActivities(mainIntent, 0) );
//    				if(mShortCut!=null){
//    					mShortCuts.add(mShortCut.get(0));//???
//    				}
    		        
    			}
    			
    		}while(c.moveToNext());
    	}
	        
	        
	        
        
        
        Log.e(TAG, mShortCuts.size()+"ShrotCuts");
    }
    
    public class ShortCutAdapter extends BaseAdapter {

		public int getCount() {
			return Math.min(32, mShortCuts.size());
		}

		public Object getItem(int position) {
			return mShortCuts.get(position % mShortCuts.size());
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			
			final ResolveInfo info = mShortCuts.get(position % mShortCuts.size());
//			final String oldname = info.activityInfo.loadLabel(getPackageManager()).toString();
			final String oldname = name.get(position);
			 ImageItemView v = new ImageItemView(DeleteShortCut.this,
					oldname, 
					info.activityInfo.loadIcon(getPackageManager()));
			
//			v.setOnClickListener(l)
//			v.setOnKeyListener(l);
			 
			 final ImageView i;
	         i = new ImageView(DeleteShortCut.this);
			 i.setImageDrawable(info.activityInfo.loadIcon(getPackageManager()));
			 
			v.setOnClickListener(new OnClickListener(){

				public void onClick(View v) {
//					Intent shortcutIntent = new Intent(ACTION_UNINSTALL_SHORTCUT);  
//			        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME,  
//			        		// 设置快捷方式的名字，可以换成自己更改的名字。
//			        		info.loadLabel(getPackageManager()).toString());
			        
//			        shortcutIntent.putExtra(EXTRA_SHORTCUT_DUPLICATE, false);  
//			        Intent intent2 = new Intent(Intent.ACTION_DELETE);  
//			        intent2.putExtra(Intent.EXTRA_SHORTCUT_NAME,  
//			        		// 设置快捷方式的名字，可以换成自己更改的名字。
//			        		info.loadLabel(getPackageManager()).toString());
//			        intent2.addCategory(Intent.CATEGORY_LAUNCHER);
//
//			        intent2.setComponent(new ComponentName(info.activityInfo.packageName,  
//			        		info.activityInfo.name));  
//			          
//			        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent2);
//			        
//			        Log.e("lalalala", info.activityInfo.name);
			        
			        
//			        try {
//						context = createPackageContext(info.activityInfo.packageName,0);
//					} catch (NameNotFoundException e) {
//						e.printStackTrace();
//					}
//			        Launcher.this.setPackageName(info.activityInfo.packageName);
//			        Log.e("lalala", context.getPackageName());
//			        Log.e("lalala", context.getResources().getResourceName(resid));
//			        Log.e("lalala",context.getApplicationInfo().name);
			        
			        //这种方式似乎不合理：
			        /*shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
			        		Intent.ShortcutIconResource.fromContext(context, 
			        				R.drawable.gtalk)); */
			        
			        
//			        Bitmap bitmap = ((BitmapDrawable) i.getDrawable()).getBitmap();
//
//			        //这个putExtra里面是要有Bitmap类型的图标的：
//			        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, 
//			        		bitmap);
//			        sendBroadcast(shortcutIntent);
			        
//			        Log.e(TAG, "sendBroadcast(shortcutIntent)");
			        
			        
			        /*String DELETE_ACTION = 
			        	"com.android.launcher.action.UNINSTALL_SHORTCUT";
			        Intent intent = new Intent(DELETE_ACTION);

			        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "闹钟");

			        ComponentName comp = new ComponentName("com.android.alarmclock",

			        "com.android.alarmclock.AlarmClock");

			        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent()

//			        .setComponent(comp).setAction("android.intent.action.MAIN"));
			        .setComponent(comp).setAction(Intent.ACTION_MAIN));

			        sendBroadcast(intent);*/
					/*String name = null;
					try {
						  name= new String(info.loadLabel(getPackageManager()).toString().getBytes("UTF-8"),"iso-8859-1");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} */ 
//					info.loadLabel(getPackageManager());
					
					/*ShowShortCut.this.getContentResolver()
					.delete(shortCutUri, "title="+name, null);*/
					
					
					final Dialog dlg=new AlertDialog.Builder(DeleteShortCut.this)
					.setTitle("您确定要删除("+oldname+")桌面快捷方式吗？")
					.setIcon(info.activityInfo.loadIcon(getPackageManager()))
					.setPositiveButton("确定", 
							new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dlg, int which) {
							//
							
							Log.e(TAG, position+"+1");
							String id = ids.get(position);
							Log.e(TAG, id);
							
							
							
							/**删除Launcher.db中的_id列*/
							DeleteShortCut.this.getContentResolver().delete(shortCutUri, "_id="+id, null);
							
							/**删除*/
							mShortCuts.remove(position);
							
							i.destroyDrawingCache();
							
							ids.remove(position);
							
//							grid.removeViewAt(position);//Adapter不支持这个方法，会报错。
							
							dlg.cancel();
//							dlg.dismiss();
							
							DeleteShortCut.this.finish();
						}
					}
					).setNegativeButton("取消", 
							new DialogInterface.OnClickListener() {
								
								public void onClick(DialogInterface dialog, int which) {
									dialog.cancel();
								}
							}
					)
					.create();
					
					dlg.show();
					

					
				}
            	
            });
			
			return v;
		}
    	
    }
    
    public class ImageItemView extends RelativeLayout {  
	      
		private TextView text;
		private ImageView image;
		
	        public ImageItemView(Context context, String title, Drawable drawable) {  
	            super(context);  
	            RelativeLayout.LayoutParams rrr = new RelativeLayout.LayoutParams(50,
	    				50);
	           rrr.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
	           image = new ImageView(context);  
	           image.setId(001);
	           image.setImageDrawable(drawable); 
	           image.setScaleType(ImageView.ScaleType.FIT_CENTER);
	           // 左，上，右，下  
	           image.setPadding(0, 0, 0, 0);
//	           addView(image, new GridView.LayoutParams(50, 50));  
	           image.setLayoutParams(new GridView.LayoutParams(50, 50));
	           addView(image, rrr);  
	           
	           RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.WRAP_CONTENT, 
						RelativeLayout.LayoutParams.WRAP_CONTENT);
			   rl.addRule(RelativeLayout.BELOW, 001);
			   rl.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
	           text = new TextView(context);  
	           text.setText(title); 
	           text.setTextColor(Color.BLUE);
	           text.setTextSize(10);
//	           text.setSingleLine();
//	           text.setEllipsize(TextUtils.TruncateAt.MARQUEE);
	           text.setMarqueeRepeatLimit(3);
	           addView(text, rl);  
	       }  
	        
	        public ImageView getImageView(){
	        	return image;
	        }

    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		menu.add("删除全部桌面快捷方式");
		
		return true;
		
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		final Dialog dlg=new AlertDialog.Builder(DeleteShortCut.this)
		.setTitle("您确定要删除全部的桌面快捷方式吗？")
		.setPositiveButton("确定", 
				new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dlg, int which) {
				//
				
				for(String id:ids){
					/**删除Launcher.db中的全部数据*/
					DeleteShortCut.this.getContentResolver().delete(shortCutUri, "_id="+id, null);
				}
				

				dlg.cancel();
//				dlg.dismiss();
				
				DeleteShortCut.this.finish();
			}
		}
		).setNegativeButton("取消", 
				new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				}
		)
		.create();
		
		dlg.show();
	
		return true;
	}
    
}
