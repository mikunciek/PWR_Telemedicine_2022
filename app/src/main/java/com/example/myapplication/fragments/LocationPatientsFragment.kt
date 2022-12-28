package com.example.myapplication.fragments

import android.Manifest.permission.ACCESS_FINE_LOCATION

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment

import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLocationPatientsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.fragment_location_patients.*


class LocationPatientsFragment : Fragment()  {

    private lateinit var binding: FragmentLocationPatientsBinding

    private lateinit var fusedLocationClient : FusedLocationProviderClient


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_patients, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())


      getLocation.setOnClickListener {

            //step 1 is check self permission
            checkLocationPermission()

            locationText.text = fusedLocationClient.toString()

        }
    }

    private fun init() {
     //  val getLocation = binding.getLocation
    }

    private fun checkLocationPermission() {

        if (checkSelfPermission(
                requireContext(),
                ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            //when permission is already grant

        } else {
            //when permission is
          //  ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))

        }
    }


/*
    fun permamentLocation(){
        // declare a global variable FusedLocationProviderClient
        lateinit var fusedLocationClient: FusedLocationProviderClient

        // in onCreate() initialize FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)


        // globally declare LocationRequest
        lateinit var locationRequest: LocationRequest

        // globally declare LocationCallback
        lateinit var locationCallback: LocationCallback


        /**
         * call this method in onCreate
         * onLocationResult call when location is changed
         */
        fun getLocationUpdates()
        {

            fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)
            locationRequest = LocationRequest()
            locationRequest.interval = 50000
            locationRequest.fastestInterval = 50000
            locationRequest.smallestDisplacement = 170f // 170 m = 0.1 mile
            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY //set according to your app function
            locationCallback = object : LocationCallback() {

                override fun onLocationResult(locationResult: LocationResult?) {
                    locationResult ?: return

                    if (locationResult.locations.isNotEmpty()) {
                        // get latest location
                        val location =
                            locationResult.lastLocation
                        // use your location object
                        // get latitude , longitude and other info from this
                    }


                }
            }
        }

        //start location updates
        fun startLocationUpdates() {
            if (checkSelfPermission(
                    requireContext(),
                    ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                null /* Looper */
            )
        }

        // stop location updates
        fun stopLocationUpdates() {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }

        // stop receiving location update when activity not visible/foreground
        fun onPause() {
            super.onPause()
            stopLocationUpdates()
        }

        // start receiving location update when activity  visible/foreground
        fun onResume() {
            super.onResume()
            startLocationUpdates()
        }

    }

 */
}