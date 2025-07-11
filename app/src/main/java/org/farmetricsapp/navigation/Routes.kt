package org.farmetricsapp.navigation

object Routes {
    // Auth routes
    const val SPLASH = "splash"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val FORGOT_PASSWORD = "forgot_password"
    
    // Main routes
    const val HOME = "home"
    const val PROFILE = "profile"
    const val SETTINGS = "settings"
    
    // Farmer routes
    const val FARMER_LIST = "farmer_list"
    const val FARMER_DETAILS = "farmer_details/{farmerId}"
    const val ADD_FARMER = "add_farmer"
    const val EDIT_FARMER = "edit_farmer/{farmerId}"
    
    // Farm routes
    const val FARM_LIST = "farm_list"
    const val FARM_DETAILS = "farm_details/{farmId}"
    const val ADD_FARM = "add_farm/{farmerId}"
    const val EDIT_FARM = "edit_farm/{farmId}"
    const val CAPTURE_FARM = "capture_farm/{farmId}"
    
    // Visit routes
    const val VISIT_LIST = "visit_list"
    const val VISIT_DETAILS = "visit_details/{visitId}"
    const val ADD_VISIT = "add_visit/{farmId}"
    const val EDIT_VISIT = "edit_visit/{visitId}"
    
    // Issue routes
    const val ISSUE_LIST = "issue_list"
    const val ISSUE_DETAILS = "issue_details/{issueId}"
    const val ADD_ISSUE = "add_issue/{farmId}"
    const val EDIT_ISSUE = "edit_issue/{issueId}"
    
    // Sync routes
    const val SYNC_STATUS = "sync_status"
    
    // Helper functions for parameterized routes
    fun farmerDetails(farmerId: String) = "farmer_details/$farmerId"
    fun editFarmer(farmerId: String) = "edit_farmer/$farmerId"
    fun farmDetails(farmId: String) = "farm_details/$farmId"
    fun addFarm(farmerId: String) = "add_farm/$farmerId"
    fun editFarm(farmId: String) = "edit_farm/$farmId"
    fun captureFarm(farmId: String) = "capture_farm/$farmId"
    fun visitDetails(visitId: String) = "visit_details/$visitId"
    fun addVisit(farmId: String) = "add_visit/$farmId"
    fun editVisit(visitId: String) = "edit_visit/$visitId"
    fun issueDetails(issueId: String) = "issue_details/$issueId"
    fun addIssue(farmId: String) = "add_issue/$farmId"
    fun editIssue(issueId: String) = "edit_issue/$issueId"
} 