package com.kiyansoftech.student.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.kiyansoftech.student.R
import com.kiyansoftech.student.ui.fragment.BottomFragments.classroom.ClassRoomFragment
import com.kiyansoftech.student.ui.fragment.BottomFragments.home.HomeFragment
import com.kiyansoftech.student.ui.fragment.BottomFragments.library.LibraryFragment
import com.kiyansoftech.student.ui.fragment.BottomFragments.more.MoreFragment
import com.kiyansoftech.student.ui.fragment.BottomFragments.notifications.NotificationsFragment
import com.kiyansoftech.student.ui.fragment.SideFragments.*
import kotlinx.android.synthetic.main.activity_main.*


class Dashboard : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var imgMenu: ImageView? = null
    private var navItemIndex: Int = 0
    private var bottomFragment: Fragment? = null
    private var sideFragment: Fragment? = null
    private var toolbar: Toolbar? = null

    // flag to load home fragment when user presses back key
    private val shouldLoadHomeFragOnBackPress = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)

        imgMenu = findViewById<View>(R.id.imgViewMenu) as ImageView
        imgMenu!!.setOnClickListener(View.OnClickListener {
            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.closeDrawer(GravityCompat.START)
            } else {
                drawer_layout.openDrawer(GravityCompat.START)
            }
        }
        )

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val sidenavView: NavigationView = findViewById(R.id.side_nav_view)
        sidenavView.bringToFront()
        sidenavView.setNavigationItemSelectedListener(this)


        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    bottomFragment = HomeFragment.newInstance()
                }
                R.id.navigation_class_room -> {
                    bottomFragment = ClassRoomFragment.newInstance()
                }
                R.id.navigation_library -> {
                    bottomFragment = LibraryFragment.newInstance()
                }
                R.id.navigation_notifications -> {
                    bottomFragment = NotificationsFragment.newInstance()
                }
                R.id.navigation_more -> {
                    bottomFragment = MoreFragment.newInstance()
                }

            }
            openFragment(bottomFragment)
            return@setOnNavigationItemSelectedListener true
        }

        bottomFragment = HomeFragment.newInstance()
        openFragment(bottomFragment)

        layProfile.setOnClickListener {
            bottomFragment = TutorFragment.newInstance()
/*
            openFragment(bottomFragment)
*/

            val bundle = Bundle()
            val myMessage = "Stackoverflow is cool!"
            bundle.putString("message", myMessage)
            val fragInfo = bottomFragment
            fragInfo?.setArguments(bundle)
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, fragInfo!!)
            transaction.addToBackStack(null)
            transaction.commit()
/*
to get data in fragmnent
            val myValue: String = this.getArguments().getString("message")
*/

        }
    }


    override fun onBackPressed() {
        val drawer =
            findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    fun openFragment(fragment: Fragment?) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment!!)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        drawer_layout.closeDrawer(GravityCompat.START)

        when (menuItem.itemId) {
            R.id.nav_mycourse -> {
                navItemIndex = 0
                sideFragment = MyCoursesFragment.newInstance()
            }
            R.id.nav_tutor -> {
                navItemIndex = 1
                sideFragment = TutorFragment.newInstance()

            }
            R.id.nav_library -> {
                navItemIndex = 2
                sideFragment = MyLibraryFragment.newInstance()

            }
            R.id.nav_classlive -> {
                navItemIndex = 3
                sideFragment = LiveStreamFragment.newInstance()

            }
            R.id.nav_notes -> {
                navItemIndex = 4
                sideFragment = NotesFragment.newInstance()

            }
            R.id.nav_assignments -> {
                navItemIndex = 5
                sideFragment = AssignmentFragment.newInstance()

            }
            R.id.nav_classmates -> {
                navItemIndex = 6
                sideFragment = ClassmatesFragment.newInstance()

            }
            R.id.nav_message -> {
                navItemIndex = 7
                sideFragment = MessageFragment.newInstance()

            }
            R.id.nav_mockexam -> {
                navItemIndex = 8
                sideFragment = MockExamFragment.newInstance()

            }
            R.id.nav_mentors -> {
                navItemIndex = 9
                sideFragment = MyMentorsFragment.newInstance()

            }
            R.id.nav_startCourse -> {
                navItemIndex = 10
                sideFragment = StartCourseFragment.newInstance()

            }
            R.id.nav_payments -> {
                navItemIndex = 11
                sideFragment = PaymentFragment.newInstance()

            }
            R.id.nav_locationfinder -> {
                navItemIndex = 12
                sideFragment = LocationFInderFragment.newInstance()

            }
            R.id.nav_mycalender -> {
                navItemIndex = 13
                sideFragment = MyCalenderFragment.newInstance()

            }
            else -> navItemIndex = 0
        }

        //Checking if the item is in checked state or not, if not make it in checked state
        menuItem.isChecked = !menuItem.isChecked
        menuItem.isChecked = true

        openFragment(sideFragment)
        return true
    }
}
