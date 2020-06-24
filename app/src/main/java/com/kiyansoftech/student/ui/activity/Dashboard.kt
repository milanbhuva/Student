package com.kiyansoftech.student.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.kiyansoftech.student.Global.BaseActivity
import com.kiyansoftech.student.R
import com.kiyansoftech.student.model.GetMyClassrooms.GetMyClassroom
import com.kiyansoftech.student.model.MyAssignments.MyAssignment
import com.kiyansoftech.student.ui.fragment.BottomFragments.ExamPortalFragment
import com.kiyansoftech.student.ui.fragment.BottomFragments.classroom.ClassRoomFragment
import com.kiyansoftech.student.ui.fragment.BottomFragments.home.HomeFragment
import com.kiyansoftech.student.ui.fragment.BottomFragments.library.LibraryFragment
import com.kiyansoftech.student.ui.fragment.BottomFragments.messages.MainMessageFragment
import com.kiyansoftech.student.ui.fragment.BottomFragments.more.MoreFragment
import com.kiyansoftech.student.ui.fragment.BottomFragments.notifications.NotificationsFragment
import com.kiyansoftech.student.ui.fragment.SideFragments.*
import com.oeye.network.Networking
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Dashboard : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var imgMenu: ImageView? = null
    private var navItemIndex: Int = 0
    private var bottomFragment: Fragment? = null
    private var sideFragment: Fragment? = null
    private var toolbar: Toolbar? = null
    // var userid: String = ""
    // var classroomid: String = ""

    var bottomposition = 0

    // flag to load home fragment when user presses back key
    private val shouldLoadHomeFragOnBackPress = true
    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)

        //userid = intent.getStringExtra("userid")
     //   imgMenu = findViewById<View>(R.id.imgViewMenu) as ImageView
/*
        imgMenu!!.setOnClickListener(View.OnClickListener {
            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.closeDrawer(GravityCompat.START)
            } else {
                drawer_layout.openDrawer(GravityCompat.START)
            }
        }
        )
*/

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val sidenavView: NavigationView = findViewById(R.id.side_nav_view)
        val nav_Menu: Menu = sidenavView.getMenu()

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

                    nav_Menu.findItem(R.id.nav_myclassroom).setVisible(true)
                    nav_Menu.findItem(R.id.nav_message).setVisible(true)
                    nav_Menu.findItem(R.id.nav_library).setVisible(true)
                    nav_Menu.findItem(R.id.nav_classmates).setVisible(true)
                    nav_Menu.findItem(R.id.nav_tutor).setVisible(true)
                    nav_Menu.findItem(R.id.nav_trainvid).setVisible(true)
                    nav_Menu.findItem(R.id.nav_newcourse).setVisible(true)
                    nav_Menu.findItem(R.id.nav_courseguide).setVisible(true)
                    nav_Menu.findItem(R.id.nav_mockexam).setVisible(true)
                    nav_Menu.findItem(R.id.nav_examportal).setVisible(true)
                    nav_Menu.findItem(R.id.nav_checkresult).setVisible(true)
                    nav_Menu.findItem(R.id.nav_assignments).setVisible(true)
                    nav_Menu.findItem(R.id.nav_mentors).setVisible(true)
                    nav_Menu.findItem(R.id.nav_notes).setVisible(true)
                    nav_Menu.findItem(R.id.nav_mycalender).setVisible(true)
                    nav_Menu.findItem(R.id.nav_shareapp).setVisible(true)
                    nav_Menu.findItem(R.id.nav_playgames).setVisible(true)
                    nav_Menu.findItem(R.id.nav_payments).setVisible(true)
                    nav_Menu.findItem(R.id.nav_mycertificate).setVisible(true)


                    nav_Menu.findItem(R.id.nav_mycourse).setVisible(false)
                    nav_Menu.findItem(R.id.nav_tutor2).setVisible(false)
                    nav_Menu.findItem(R.id.nav_classmates2).setVisible(false)
                    nav_Menu.findItem(R.id.nav_library2).setVisible(false)
                    nav_Menu.findItem(R.id.nav_assignments2).setVisible(false)
                    nav_Menu.findItem(R.id.nav_notes2).setVisible(false)
                    nav_Menu.findItem(R.id.nav_mockexam2).setVisible(false)
                    nav_Menu.findItem(R.id.nav_message2).setVisible(false)

                    nav_Menu.findItem(R.id.nav_courseguide3).setVisible(false)
                    nav_Menu.findItem(R.id.nav_studyres).setVisible(false)
                    nav_Menu.findItem(R.id.nav_coursemanual).setVisible(false)
                    nav_Menu.findItem(R.id.nav_trainvid3).setVisible(false)


                    nav_Menu.findItem(R.id.nav_locationfinder).setVisible(false)
                    nav_Menu.findItem(R.id.nav_classlive).setVisible(false)
                    nav_Menu.findItem(R.id.nav_startCourse).setVisible(false)

                    bottomFragment = HomeFragment.newInstance()
                    if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
//                        drawer_layout.closeDrawer(GravityCompat.START)
                    } else {
                        drawer_layout.openDrawer(GravityCompat.START)
                    }

                }
                R.id.navigation_class_room -> {

                    nav_Menu.findItem(R.id.nav_myclassroom).setVisible(false)
                    nav_Menu.findItem(R.id.nav_message).setVisible(false)
                    nav_Menu.findItem(R.id.nav_library).setVisible(false)
                    nav_Menu.findItem(R.id.nav_classmates).setVisible(false)
                    nav_Menu.findItem(R.id.nav_tutor).setVisible(false)
                    nav_Menu.findItem(R.id.nav_trainvid).setVisible(false)
                    nav_Menu.findItem(R.id.nav_newcourse).setVisible(false)
                    nav_Menu.findItem(R.id.nav_courseguide).setVisible(false)
                    nav_Menu.findItem(R.id.nav_mockexam).setVisible(false)
                    nav_Menu.findItem(R.id.nav_examportal).setVisible(false)
                    nav_Menu.findItem(R.id.nav_checkresult).setVisible(false)
                    nav_Menu.findItem(R.id.nav_assignments).setVisible(false)
                    nav_Menu.findItem(R.id.nav_mentors).setVisible(false)
                    nav_Menu.findItem(R.id.nav_notes).setVisible(false)
                    nav_Menu.findItem(R.id.nav_mycalender).setVisible(false)
                    nav_Menu.findItem(R.id.nav_shareapp).setVisible(false)
                    nav_Menu.findItem(R.id.nav_playgames).setVisible(false)
                    nav_Menu.findItem(R.id.nav_payments).setVisible(false)
                    nav_Menu.findItem(R.id.nav_mycertificate).setVisible(false)


                    nav_Menu.findItem(R.id.nav_mycourse).setVisible(true)
                    nav_Menu.findItem(R.id.nav_tutor2).setVisible(true)
                    nav_Menu.findItem(R.id.nav_classmates2).setVisible(true)
                    nav_Menu.findItem(R.id.nav_library2).setVisible(true)
                    nav_Menu.findItem(R.id.nav_assignments2).setVisible(true)
                    nav_Menu.findItem(R.id.nav_notes2).setVisible(true)
                    nav_Menu.findItem(R.id.nav_mockexam2).setVisible(true)
                    nav_Menu.findItem(R.id.nav_message2).setVisible(true)

                    nav_Menu.findItem(R.id.nav_courseguide3).setVisible(false)
                    nav_Menu.findItem(R.id.nav_studyres).setVisible(false)
                    nav_Menu.findItem(R.id.nav_coursemanual).setVisible(false)
                    nav_Menu.findItem(R.id.nav_trainvid3).setVisible(false)

                    nav_Menu.findItem(R.id.nav_locationfinder).setVisible(false)
                    nav_Menu.findItem(R.id.nav_classlive).setVisible(false)
                    nav_Menu.findItem(R.id.nav_startCourse).setVisible(false)
                    bottomFragment = ClassRoomFragment.newInstance()
                    if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    } else {
                        drawer_layout.openDrawer(GravityCompat.START)
                    }
                    // getclassroom()

                }
                R.id.navigation_message -> {
                    bottomFragment = MainMessageFragment.newInstance()


                   /* if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                        drawer_layout.closeDrawer(GravityCompat.START)
                    }*/

                }
                R.id.navigation_library -> {

                    nav_Menu.findItem(R.id.nav_myclassroom).setVisible(false)
                    nav_Menu.findItem(R.id.nav_message).setVisible(false)
                    nav_Menu.findItem(R.id.nav_library).setVisible(false)
                    nav_Menu.findItem(R.id.nav_classmates).setVisible(false)
                    nav_Menu.findItem(R.id.nav_tutor).setVisible(false)
                    nav_Menu.findItem(R.id.nav_trainvid).setVisible(false)
                    nav_Menu.findItem(R.id.nav_newcourse).setVisible(false)
                    nav_Menu.findItem(R.id.nav_courseguide).setVisible(false)
                    nav_Menu.findItem(R.id.nav_mockexam).setVisible(false)
                    nav_Menu.findItem(R.id.nav_examportal).setVisible(false)
                    nav_Menu.findItem(R.id.nav_checkresult).setVisible(false)
                    nav_Menu.findItem(R.id.nav_assignments).setVisible(false)
                    nav_Menu.findItem(R.id.nav_mentors).setVisible(false)
                    nav_Menu.findItem(R.id.nav_notes).setVisible(false)
                    nav_Menu.findItem(R.id.nav_mycalender).setVisible(false)
                    nav_Menu.findItem(R.id.nav_shareapp).setVisible(false)
                    nav_Menu.findItem(R.id.nav_playgames).setVisible(false)
                    nav_Menu.findItem(R.id.nav_payments).setVisible(false)
                    nav_Menu.findItem(R.id.nav_mycertificate).setVisible(false)


                    nav_Menu.findItem(R.id.nav_mycourse).setVisible(false)
                    nav_Menu.findItem(R.id.nav_tutor2).setVisible(false)
                    nav_Menu.findItem(R.id.nav_classmates2).setVisible(false)
                    nav_Menu.findItem(R.id.nav_library2).setVisible(false)
                    nav_Menu.findItem(R.id.nav_assignments2).setVisible(false)
                    nav_Menu.findItem(R.id.nav_notes2).setVisible(false)
                    nav_Menu.findItem(R.id.nav_mockexam2).setVisible(false)
                    nav_Menu.findItem(R.id.nav_message2).setVisible(false)

                    nav_Menu.findItem(R.id.nav_courseguide3).setVisible(true)
                    nav_Menu.findItem(R.id.nav_studyres).setVisible(true)
                    nav_Menu.findItem(R.id.nav_coursemanual).setVisible(true)
                    nav_Menu.findItem(R.id.nav_trainvid3).setVisible(true)

                    nav_Menu.findItem(R.id.nav_locationfinder).setVisible(false)
                    nav_Menu.findItem(R.id.nav_classlive).setVisible(false)
                    nav_Menu.findItem(R.id.nav_startCourse).setVisible(false)

                    bottomFragment = ExamPortalFragment.newInstance()
                    if (drawer_layout.isDrawerOpen(GravityCompat.START)) {

                    } else {
                        drawer_layout.openDrawer(GravityCompat.START)

                    }

                }
                R.id.navigation_account -> {
                    bottomFragment = MoreFragment.newInstance()
                    if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                        drawer_layout.closeDrawer(GravityCompat.START)
                    }

                }

            }
            openFragment(bottomFragment)
            return@setOnNavigationItemSelectedListener true
        }

        bottomFragment = HomeFragment.newInstance()
        openFragment(bottomFragment)

/*
        layProfile.setOnClickListener {
            bottomFragment = TutorFragment.newInstance()
*/
/*
            openFragment(bottomFragment)
*//*


            val bundle = Bundle()
            val myMessage = "cool!"
            bundle.putString("message", myMessage)
            val fragInfo = bottomFragment
            fragInfo?.setArguments(bundle)
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, fragInfo!!)
            transaction.addToBackStack(null)
            transaction.commit()
*/
/*
to get data in fragmnent
            val myValue: String = this.getArguments().getString("message")
*//*


        }
*/
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.settingsmenu, menu)
        return true
    }


/*
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(applicationContext, "click on setting", Toast.LENGTH_LONG).show()
                true
            }
            R.id.action_share ->{
                Toast.makeText(applicationContext, "click on share", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.action_exit ->{
                Toast.makeText(applicationContext, "click on exit", Toast.LENGTH_LONG).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
*/

    private fun getclassroom() {

        Networking.with().getServices().getMyClassroom(session.user?.userId.toString())
            .enqueue(object : Callback<GetMyClassroom> {
                override fun onFailure(call: Call<GetMyClassroom>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<GetMyClassroom>,
                    response: Response<GetMyClassroom>
                ) {
                    if (response.body()?.status == 0) {

                        Toast.makeText(
                            applicationContext,
                            response.body()?.message,
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            response.body()?.message,
                            Toast.LENGTH_LONG
                        ).show()
/*
                        goToActivityAndClearTask<Dashboard>()
*/
                    }
                }
            })

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
        /* val bundle = Bundle()
         bundle.putString("userid", userid)
         fragment?.setArguments(bundle)*/
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment!!)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        drawer_layout.closeDrawer(GravityCompat.START)

        when (menuItem.itemId) {
            R.id.nav_myclassroom -> {
                //navItemIndex = 0
                sideFragment = MessageFragment.newInstance()
            }
            R.id.nav_message -> {
                //  navItemIndex = 1
                sideFragment = MainMessageFragment.newInstance()

            }
            R.id.nav_library -> {
                // navItemIndex = 2
                sideFragment = MyLibraryFragment.newInstance()

            }
            R.id.nav_classmates -> {
                // navItemIndex = 3
                sideFragment = ClassmatesFragment.newInstance()

            }
            R.id.nav_tutor -> {
                //navItemIndex = 4
                sideFragment = TutorFragment.newInstance()

            }
            R.id.nav_trainvid -> {
                //navItemIndex = 5
                sideFragment = LibraryFragment.newInstance()

            }
            R.id.nav_newcourse -> {
                // navItemIndex = 6
                sideFragment = StartCourseFragment.newInstance()

            }
            R.id.nav_courseguide -> {
                //  navItemIndex = 7
                sideFragment = MessageFragment.newInstance()

            }
            R.id.nav_mockexam -> {
                // navItemIndex = 8
                sideFragment = MockExamFragment.newInstance()

            }
            R.id.nav_examportal -> {
                // navItemIndex = 9
                sideFragment = MockExamFragment.newInstance()

            }
            R.id.nav_checkresult -> {
                // navItemIndex = 10
                //   sideFragment = StartCourseFragment.newInstance()

            }
            R.id.nav_assignments -> {
                // navItemIndex = 11
                sideFragment = AssignmentFragment.newInstance()

            }
            R.id.nav_mentors -> {
                // navItemIndex = 12
                sideFragment = MyMentorsFragment.newInstance()

            }
            R.id.nav_notes -> {
                //navItemIndex = 13
                sideFragment = NotesFragment.newInstance()

            }
            R.id.nav_mycalender -> {
                //navItemIndex = 13
                sideFragment = MyCalenderFragment.newInstance()

            }
            R.id.nav_shareapp -> {
                //navItemIndex = 13
                // sideFragment = HomeFragment.newInstance()

            }
            R.id.nav_playgames -> {
                //navItemIndex = 13
                sideFragment = PlayGamesFragment.newInstance()

            }
            R.id.nav_payments -> {
                //navItemIndex = 13
                sideFragment = PaymentFragment.newInstance()

            }
            R.id.nav_mycertificate -> {
                //navItemIndex = 13
                sideFragment = MycertificateFragment.newInstance()
            }
            R.id.nav_mycourse -> {
                //navItemIndex = 13
                sideFragment = MyCoursesFragment.newInstance()
            }
            R.id.nav_tutor2 -> {
                //navItemIndex = 13
                sideFragment = TutorFragment.newInstance()
            }
            R.id.nav_classmates2 -> {
                //navItemIndex = 13
                sideFragment = ClassmatesFragment.newInstance()
            }
            R.id.nav_library2 -> {
                //navItemIndex = 13
                sideFragment = LibraryFragment.newInstance()
            }
            R.id.nav_assignments2 -> {
                //navItemIndex = 13
                sideFragment = AssignmentFragment.newInstance()
            }
            R.id.nav_notes2 -> {
                //navItemIndex = 13
                sideFragment = NotesFragment.newInstance()
            }
            R.id.nav_mockexam2 -> {
                //navItemIndex = 13
                sideFragment = MockExamFragment.newInstance()
            }
            R.id.nav_message2 -> {
                //navItemIndex = 13
                sideFragment = MessageFragment.newInstance()
            }
            R.id.nav_courseguide3 -> {
                //navItemIndex = 13
                sideFragment = CourseGuideFragment.newInstance()
            }
            R.id.nav_studyres -> {
                //navItemIndex = 13
                sideFragment = StudyResFragment.newInstance()
            }
            R.id.nav_coursemanual -> {
                //navItemIndex = 13
               // sideFragment = StudyResFragment.newInstance()
            }
            R.id.nav_trainvid3 -> {
                //navItemIndex = 13
                // sideFragment = StudyResFragment.newInstance()
            }
//            else -> navItemIndex = 0
        }

        //Checking if the item is in checked state or not, if not make it in checked state
        menuItem.isChecked = !menuItem.isChecked
        menuItem.isChecked = true

        openFragment(sideFragment)
        return true
    }
}
