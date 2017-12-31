#

I wanted to make a NavigationDrawer that has an ExpandableListView inside since my menu has a lot of items in it. So I created this sample project. Also ExpandableListView is Animated.

# Setup

1. Add following dependencies to build.gradle

```
    compile 'com.android.support:appcompat-v7:26.1.0'
    compile 'com.android.support.constraint:constraint-layout:1.0.2'
    compile 'com.android.support:design:26.1.0'

```

2. In your XML file add this custom AnimatedExpandableListView

```
		<ces.genius.com.navigationdrawer.drawer.AnimatedExpandableListView
		        android:id="@+id/drawer_left"
		        android:layout_width="@dimen/navigation_drawer_width"
		        android:layout_height="match_parent"
		        android:layout_gravity="left|start"
		        android:background="@color/list_background"
		        android:divider="@color/list_divider"
		        android:dividerHeight="@dimen/nav_drawer_divider_height" />
```

## Deployment

![alt text](https://github.com/manjeet-thadani/Temperature-Detector/blob/master/Extras/Images/app01.png)		
![alt text](https://github.com/manjeet-thadani/Temperature-Detector/blob/master/Extras/Images/app02.png)
