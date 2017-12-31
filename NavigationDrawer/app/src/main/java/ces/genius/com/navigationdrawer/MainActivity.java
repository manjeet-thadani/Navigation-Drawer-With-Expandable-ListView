package ces.genius.com.navigationdrawer;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ces.genius.com.navigationdrawer.drawer.AnimatedExpandableListView;
import ces.genius.com.navigationdrawer.drawer.GroupItem;
import ces.genius.com.navigationdrawer.drawer.NavDrawerAdapter;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavDrawerAdapter adapter;
    private DrawerLayout drawer;
    private AnimatedExpandableListView drawerList;
    private List<GroupItem> items;
    private int lastExpandedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initDrawer();
    }

    private void initDrawer() {
        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.drawerList = (AnimatedExpandableListView) findViewById(R.id.drawer_left);

        // For Expanding Nav Drawer Upto 90% of the screen size.
        double width = getResources().getDisplayMetrics().widthPixels * .90;
        int width1 = (int) width;
        DrawerLayout.LayoutParams params = (android.support.v4.widget.DrawerLayout.LayoutParams) drawerList.getLayoutParams();
        params.width = width1;
        drawerList.setLayoutParams(params);

        prepareListData();

        this.adapter = new NavDrawerAdapter(this);
        this.adapter.setData(this.items);
        this.drawerList.addHeaderView(getLayoutInflater().inflate(R.layout.nav_header, null, false));

        this.drawerList.setAdapter(this.adapter);

        this.drawerList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (MainActivity.this.drawerList.isGroupExpanded(groupPosition)) {
                    MainActivity.this.drawerList.collapseGroupWithAnimation(groupPosition);
                } else {
                    MainActivity.this.drawerList.expandGroupWithAnimation(groupPosition);
                }
                return true;
            }
        });

        this.drawerList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (!(MainActivity.this.lastExpandedPosition == -1 || groupPosition == MainActivity.this.lastExpandedPosition)) {
                    MainActivity.this.drawerList.collapseGroup(MainActivity.this.lastExpandedPosition);
                }
                MainActivity.this.lastExpandedPosition = groupPosition;
            }
        });

        this.drawerList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override

            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                MainActivity.this.drawerList.collapseGroup(MainActivity.this.lastExpandedPosition);

                String groupName = ((GroupItem) MainActivity.this.items.get(groupPosition)).title;
                String childName = (String) ((GroupItem) MainActivity.this.items.get(groupPosition)).items.get(childPosition);

                Toast.makeText(MainActivity.this, "Clicked: " + groupName + " - " + childName, Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        this.actionBarDrawerToggle = new ActionBarDrawerToggle(this, this.drawer, this.toolbar, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                MainActivity.this.drawerList.collapseGroup(MainActivity.this.lastExpandedPosition);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        this.drawer.setDrawerListener(this.actionBarDrawerToggle);
        this.actionBarDrawerToggle.syncState();
    }

    private void prepareListData() {
        items = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String groupName = "Group " + String.valueOf(i);

            List<String> childItems = new ArrayList<>();

            for (int j = 1; j <= 4; j++) {
                childItems.add("Child " + String.valueOf(j));
            }

            GroupItem item = new GroupItem();
            item.title = groupName;
            item.items = childItems;

            items.add(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (this.drawer.isDrawerOpen(GravityCompat.START)) {
            this.drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (this.drawer.isDrawerOpen(GravityCompat.START)) {
            this.drawer.closeDrawer(GravityCompat.START);
        }
    }
}
