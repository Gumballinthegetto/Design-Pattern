package kh.edu.rupp.ite.visitmekotlin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kh.edu.rupp.ite.visitmekotlin.R
import kh.edu.rupp.ite.visitmekotlin.databinding.ActivityMainBinding
import kh.edu.rupp.ite.visitmekotlin.ui.fragment.HomeFragment
import kh.edu.rupp.ite.visitmekotlin.ui.fragment.MoreFragment
import kh.edu.rupp.ite.visitmekotlin.ui.fragment.ProfileFragment
import kh.edu.rupp.ite.visitmekotlin.ui.fragment.ProvincesFragment
import kh.edu.rupp.ite.visitmekotlin.ui.fragment.SearchFragment

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val provincesFragment = ProvincesFragment()
    private val searchFragment = SearchFragment()
    private val profileFragment = ProfileFragment()
    private val moreFragment = MoreFragment()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showFragment(homeFragment)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeMenu -> {
                    showFragment(homeFragment)
                    true
                }
                R.id.provincesMenu -> {
                    showFragment(provincesFragment)
                    true
                }
                R.id.searchMenu -> {
                    showFragment(searchFragment)
                    true
                }
                R.id.profileMenu -> {
                    showFragment(profileFragment)
                    true
                }
                R.id.moreMenu -> {
                    showFragment(moreFragment)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layoutFragment, fragment)
        fragmentTransaction.commit()
    }
}