package org.zeropage.apps.zeropage.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.zeropage.apps.zeropage.R;
import org.zeropage.apps.zeropage.data_singleton.User;
import org.zeropage.apps.zeropage.main.viewpager.ChangeFragment;
import org.zeropage.apps.zeropage.main.viewpager.NotiFragment;
import org.zeropage.apps.zeropage.main.viewpager.SearchFragment;
import org.zeropage.apps.zeropage.main.viewpager.ViewPagerAdapter;
import org.zeropage.apps.zeropage.main.viewpager.WebViewFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        NotiFragment.OnFragmentInteractionListener,
        ChangeFragment.OnFragmentInteractionListener,
        SearchFragment.OnFragmentInteractionListener,
        ViewPager.OnPageChangeListener, TabLayout.OnTabSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private TabLayout tabLayout;

    private BackPressCloseHandler backPressCloseHandler;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        //actionbar는 툴바와 탭으로 이뤄짐
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //탭추가
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("공지사항"));
        tabLayout.addTab(tabLayout.newTab().setText("위키 변경점"));
        tabLayout.addTab(tabLayout.newTab().setText("위키 검색"));
        tabLayout.addOnTabSelectedListener(this);

        //하단 content는 viewPager
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOnPageChangeListener(this);

        //navigator는 drawerlayout
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //네비게이션뷰 헤더(프로필) 초기화
        View view = navigationView.getHeaderView(0);
        TextView nickNameTextView = (TextView) view.findViewById(R.id.nav_header_nickname);
        TextView rankTextView = (TextView) view.findViewById(R.id.nav_header_rank);

        //유저 정보 입력
        nickNameTextView.setText(User.getInstance().getNickname());
        rankTextView.setText(User.getInstance().getRank());

        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    //뒤로가기
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(!canCurrentPageGoBack()){
            backPressCloseHandler.onBackPressed();
        }

        currentPageGoBack();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //우측 상단 옵션
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getBaseContext(), "설정 준비 중", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //네비게이션 리스트
   @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent = null;

        if (id == R.id.menu_intro) {
             intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://zeropage.org/intro"));
        } else if (id == R.id.menu_rule) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://zeropage.org/regulation"));
        } else if (id == R.id.menu_push) {
            intent = new Intent(MainActivity.this, PushListActivity.class);
        }

        if(intent != null){
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Fragment 인터랙션
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    //view pager가 바뀔때마다 tab index도 변경
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabLayout.setScrollPosition(position, 0, true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //tab layout이 눌릴때마다 view pager index 변경
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public boolean canCurrentPageGoBack(){
        WebViewFragment webViewFragment = (WebViewFragment) viewPagerAdapter.getItem(viewPager.getCurrentItem());
        return webViewFragment.canGoBack();
    }

    public void currentPageGoBack(){
        WebViewFragment webViewFragment = (WebViewFragment) viewPagerAdapter.getItem(viewPager.getCurrentItem());
        webViewFragment.goBack();
    }
}
