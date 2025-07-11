package org.farmetricsapp.ui.components.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.farmetricsapp.domain.model.District
import org.farmetricsapp.domain.model.GhanaLocations
import org.farmetricsapp.domain.model.Location
import org.farmetricsapp.domain.model.Region
import org.farmetricsapp.ui.theme.TextFieldShape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDropdowns(
    selectedRegionId: String,
    selectedDistrictId: String,
    selectedLocationId: String,
    onRegionSelected: (String) -> Unit,
    onDistrictSelected: (String) -> Unit,
    onLocationSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    var expandedRegion by remember { mutableStateOf(false) }
    var expandedDistrict by remember { mutableStateOf(false) }
    var expandedLocation by remember { mutableStateOf(false) }
    
    val selectedRegion = remember(selectedRegionId) {
        GhanaLocations.regions.find { it.id == selectedRegionId }
    }
    
    val selectedDistrict = remember(selectedDistrictId) {
        selectedRegion?.districts?.find { it.id == selectedDistrictId }
    }
    
    val selectedLocation = remember(selectedLocationId) {
        selectedDistrict?.locations?.find { it.id == selectedLocationId }
    }
    
    Column(modifier = modifier.fillMaxWidth()) {
        // Region Dropdown
        ExposedDropdownMenuBox(
            expanded = expandedRegion,
            onExpandedChange = { expandedRegion = it }
        ) {
            OutlinedTextField(
                value = selectedRegion?.name ?: "",
                onValueChange = {},
                readOnly = true,
                label = { Text("Region") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedRegion) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                shape = TextFieldShape,
                isError = isError
            )
            
            ExposedDropdownMenu(
                expanded = expandedRegion,
                onDismissRequest = { expandedRegion = false }
            ) {
                GhanaLocations.regions.forEach { region ->
                    DropdownMenuItem(
                        text = { Text(region.name) },
                        onClick = {
                            onRegionSelected(region.id)
                            onDistrictSelected("")
                            onLocationSelected("")
                            expandedRegion = false
                        }
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // District Dropdown
        ExposedDropdownMenuBox(
            expanded = expandedDistrict,
            onExpandedChange = { expandedDistrict = it }
        ) {
            OutlinedTextField(
                value = selectedDistrict?.name ?: "",
                onValueChange = {},
                readOnly = true,
                enabled = selectedRegion != null,
                label = { Text("District") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedDistrict) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                shape = TextFieldShape,
                isError = isError
            )
            
            ExposedDropdownMenu(
                expanded = expandedDistrict,
                onDismissRequest = { expandedDistrict = false }
            ) {
                selectedRegion?.districts?.forEach { district ->
                    DropdownMenuItem(
                        text = { Text(district.name) },
                        onClick = {
                            onDistrictSelected(district.id)
                            onLocationSelected("")
                            expandedDistrict = false
                        }
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Location Dropdown
        ExposedDropdownMenuBox(
            expanded = expandedLocation,
            onExpandedChange = { expandedLocation = it }
        ) {
            OutlinedTextField(
                value = selectedLocation?.name ?: "",
                onValueChange = {},
                readOnly = true,
                enabled = selectedDistrict != null,
                label = { Text("Location") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedLocation) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                shape = TextFieldShape,
                isError = isError
            )
            
            ExposedDropdownMenu(
                expanded = expandedLocation,
                onDismissRequest = { expandedLocation = false }
            ) {
                selectedDistrict?.locations?.forEach { location ->
                    DropdownMenuItem(
                        text = { Text(location.name) },
                        onClick = {
                            onLocationSelected(location.id)
                            expandedLocation = false
                        }
                    )
                }
            }
        }
        
        AnimatedVisibility(visible = isError && errorMessage != null) {
            Text(
                text = errorMessage ?: "",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }
    }
} 