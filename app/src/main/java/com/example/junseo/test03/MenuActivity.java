package com.example.junseo.test03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;
import android.content.Intent;
import com.google.firebase.auth.FirebaseUser;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.util.Random;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private FirebaseAuth firebaseAuth;
    private Button buttonLogout;

    LinearLayout back;
    int[] img = {R.drawable.back1, R.drawable.back2, R.drawable.back3, R.drawable.back4, R.drawable.back5, R.drawable.back6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //배경 랜덤 설정
        back = (LinearLayout)findViewById(R.id.layout);
        Random ram = new Random();
        int num = ram.nextInt(img.length);
        back.setBackgroundResource(img[num]);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final ToggleButton tb = (ToggleButton)findViewById(R.id.HearMainbutton);
        final Button button1 = (Button)findViewById(R.id.button1);
        //텍스트 입력 버튼
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent ttsIntent = new Intent(MenuActivity.this, TTSActivity.class);
                MenuActivity.this.startActivity(ttsIntent);
            }
        });
        final Button button2 = (Button)findViewById(R.id.button2);
        //상용구 버튼
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent macroIntent = new Intent(MenuActivity.this, MacroActivity.class);
                MenuActivity.this.startActivity(macroIntent);
            }
        });
        final Button button3 = (Button)findViewById(R.id.button3);
        //STT 버튼
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent sttIntent = new Intent(MenuActivity.this, STTActivity.class);
                MenuActivity.this.startActivity(sttIntent);
            }
        });
        // 솔직히 fab은 필요 없잖아요? 그쵸?
      //  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      /*  fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        tb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                if (tb.isChecked()) {
                    tb.setBackgroundDrawable(getResources().getDrawable(R.drawable.hearobutton));
                }else
                {
                    tb.setBackgroundDrawable(getResources().getDrawable(R.drawable.hearobutton));
                }
            }
        });
        //메인 버튼 이미지 토글.
        if(tb.isChecked()){
            tb.setBackgroundDrawable(getResources().getDrawable(R.drawable.hearobuttonon));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //buttonLogout = (Button) findViewById(R.id.b+uttonLogout);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));

        }
        FirebaseUser user = firebaseAuth.getCurrentUser();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
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
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_myinfo) {
            startActivity(new Intent(this, ProfileActivity.class)); //내 정보
        } else if (id == R.id.nav_version) {
            startActivity(new Intent(this, VersionActivity.class)); //앱 버전
        } else if (id == R.id.nav_help) {
            startActivity(new Intent(this,HelpActivity.class)); //도움말
        } else if (id == R.id.nav_module) {

        } else if (id == R.id.nav_alert) {
            startActivity(new Intent(this,AlarmActivity.class)); //알림 설정
        } else if (id == R.id.Logout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, MainActivity.class)); //로그아웃
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}