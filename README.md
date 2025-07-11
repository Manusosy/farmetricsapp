# Farmetrics Field Officer Mobile App

## Overview
Farmetrics Field Officer Mobile App is a native Android application built with Kotlin and Jetpack Compose, designed for agricultural field officers in Ghana. The app enables efficient farm data collection, offline-first operation, and seamless synchronization with a Supabase backend.

## Tech Stack
- **Frontend**: Kotlin + Jetpack Compose
- **Backend**: Supabase (Auth, Database, Storage)
- **Local Storage**: Room Database
- **Maps**: Mapbox SDK
- **Image Processing**: CameraX + ExifInterface
- **Background Processing**: WorkManager
- **Network**: Retrofit + Supabase SDK

## Project Setup
1. Clone the repository
2. Add required environment variables (Supabase URL, API Key, Mapbox Token)
3. Sync Gradle dependencies
4. Run the application

## Implementation Roadmap

### Phase 1: Project Setup and Authentication [In Progress]
- [x] Configure Supabase integration
  - [x] Setup Supabase client
  - [x] Configure authentication
  - [x] Setup database tables and schemas
- [x] Setup Room Database
  - [x] Define entities
  - [x] Create DAOs
  - [x] Configure database module
- [x] Implement network utilities
  - [x] API response models
  - [x] Repository interfaces
  - [x] Repository implementations
  - [x] Error handling utilities
- [x] Configure WorkManager
  - [x] Setup sync workers
  - [x] Configure periodic sync
  - [x] Implement sync manager
- [x] Setup dependency injection
  - [x] Database module
  - [x] Network module
  - [x] Repository module
  - [x] WorkManager module

### Phase 2: Core Infrastructure [In Progress]
- [x] Setup navigation architecture
  - [x] Navigation graph
  - [x] Route constants
  - [x] Navigation composables
- [x] Implement theme and design system
  - [x] Colors and typography
  - [x] Component themes
  - [x] Dark mode support
  - [x] Dimensions and spacing
  - [x] Shape system
- [x] Create base components
  - [x] Loading states
  - [x] Error states
  - [x] Common inputs
  - [x] Custom buttons
- [ ] Setup offline-first architecture
  - [x] Data sync workers
  - [x] Conflict resolution
  - [x] Retry mechanisms
- [ ] Implement sync mechanism
  - [x] Background sync service
  - [x] Sync status tracking
  - [x] Network state monitoring

### Phase 3: Authentication & Onboarding [ ]
- [ ] Progressive Sign-up Flow
  - [ ] Account Details Page
  - [ ] Demographics & Region Selection
  - [ ] Confirmation & Terms
- [ ] Authentication State Management
- [ ] User Session Management

### Phase 4: Main Features - Part 1 [ ]
- [ ] Home Dashboard
  - [ ] Welcome Screen
  - [ ] Statistics Cards
  - [ ] Status Indicators
- [ ] Profile Management
  - [ ] View Profile
  - [ ] Status Display
  - [ ] Region Assignment
  - [ ] Logout

### Phase 5: Farm Management [ ]
- [ ] Farms List
  - [ ] Farm Cards
  - [ ] Status Indicators
  - [ ] Search & Filter
- [ ] Visit Tracker
  - [ ] Visit Tabs
  - [ ] Data Collection Forms
  - [ ] Progress Tracking

### Phase 6: Data Collection [ ]
- [ ] Farm Polygon Capture
  - [ ] Mapbox Integration
  - [ ] GeoJSON Conversion
  - [ ] Offline Storage
- [ ] Media Capture
  - [ ] CameraX Implementation
  - [ ] EXIF Data Processing
  - [ ] Local Storage

### Phase 7: Farmer Management [ ]
- [ ] Add New Farmer Flow
  - [ ] Personal Info
  - [ ] Region Selection
  - [ ] Farm Information
  - [ ] Review & Submit
- [ ] Add New Farm Flow
  - [ ] Basic Information
  - [ ] Location Capture
  - [ ] Environmental Data
  - [ ] Media Upload

### Phase 8: Visit Management [ ]
- [ ] Visit Logging
- [ ] Visit Reports
- [ ] Data Filtering
- [ ] Export Capabilities

### Phase 9: Sync & Offline Features [ ]
- [ ] Sync Center
  - [ ] Status Display
  - [ ] Manual Sync
  - [ ] Auto-sync Logic
- [ ] Issue Reporting
  - [ ] Form Implementation
  - [ ] Media Attachment
  - [ ] Location Capture

### Phase 10: Testing & Optimization [ ]
- [ ] Unit Tests
- [ ] Integration Tests
- [ ] UI Tests
- [ ] Performance Optimization
- [ ] Battery Optimization

### Phase 11: Final Polish [ ]
- [ ] Dark Mode Implementation
- [ ] Error Handling
- [ ] Loading States
- [ ] Empty States
- [ ] User Feedback

## Database Schema

### Tables
1. field_officers
   - id
   - full_name
   - email
   - phone
   - gender
   - region
   - district
   - location
   - status
   - created_at

2. farmers
   - id
   - full_name
   - gender
   - contact
   - region
   - district
   - location
   - status
   - field_officer_id
   - created_at

3. farms
   - id
   - name
   - farmer_id
   - size
   - polygon
   - soil_type
   - humidity
   - crop_types
   - tree_species
   - created_at

4. visits
   - id
   - farm_id
   - field_officer_id
   - visit_number
   - status
   - data
   - media_urls
   - created_at
   - synced_at

5. issues
   - id
   - type
   - description
   - farm_id
   - field_officer_id
   - location
   - media_urls
   - status
   - created_at

## Progress Updates
[This section will be updated as we complete each phase]

## Contributing
[Contributing guidelines will be added]

## License
[License information will be added] 