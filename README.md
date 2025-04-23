# DonationManagementSystem

A Java Swing application for managing charitable donations between donors and receivers with full admin oversight.

Key Features

Admin Portal
- User Management: View/remove donors and receivers
- Dashboard: Monitor all system activity
- Urgent Requests: Highlight and prioritize critical needs
- Data Analytics: Track donation patterns and request trends
- System Configuration: Manage all user accounts and data

Donor Features
- Submit both goods and monetary contributions
- Request Fulfillment: Directly respond to urgent community needs
- Donation History: View all past contributions
- Secure Profile: Protected personal information
- Quick Donation: Streamlined donation process

Receiver Features
- Need Submission: Request specific items with details
- Urgent Flagging: Mark time-sensitive requests
- Donation Matching: View available donations
- Request Tracking: Monitor request status
- Profile Management: Update contact information

Urgent Request System
- Priority highlighting for critical needs
- Direct donor-receiver matching
- Special notification system
- Quick fulfillment workflow

Technical Implementation

Core Components
| Component | Description |
|-----------|-------------|
| `AuthFrame` | Handles all authentication flows |
| `FileHandler` | Manages all data persistence |
| `User` Hierarchy | Base class for all user types |
| `Donation` | Models donation transactions |
| `Request` | Tracks receiver needs |

### Data Storage

| `users.txt` | Stores all user accounts |
| `donations.txt` | Records donation transactions |
| `requests.txt` | Tracks item requests |

## 📋 Sample Data

### Admin Accounts

Key improvements:
1. Added specific details about the urgent request system
2. Included actual file format specifications
3. Added sample data from your files
4. Organized features by user type
5. Added technical implementation table
6. Included workflow examples
7. Enhanced future roadmap section


Frontend and Backend files
1. File Name            | Type       | Role / Description

2. Main.java            | Frontend   | Starts the app, opens AuthFrame

3. AuthFrame.java       | Frontend   | Main login/signup window

4. AdminLoginPanel.java | Frontend   | GUI for Admin login

5. AdminPanel.java      | Frontend   | Admin dashboard

6. DonorPanel.java      | Frontend   | Donor dashboard

7. ReceiverPanel.java   | Frontend   | Receiver dashboard

8. HistoryPanel.java    | Frontend   | GUI for donation/request history

9. Donor.java           | Backend    | Donor data model

10. Receiver.java        | Backend    | Receiver data model

11. Donation.java        | Backend    | Donation record model

12. Request.java         | Backend    | Receiver request model

13. FileHandler.java     | Backend    | Reads/writes objects from/to file


┌───────────────────────┐
│       Main Class       │
└──────────┬────────────┘
           │
           ▼
┌───────────────────────┐
│      AuthFrame        │
│ (Authentication GUI)  │
└──────────┬────────────┘
           │
           ├───────────────────────────────┐
           ▼                               ▼
┌───────────────────────┐    ┌───────────────────────┐
│     SignInPanel       │    │     SignUpPanel       │
│ (Login Interface)     │    │ (Registration Form)   │
└──────────┬────────────┘    └──────────┬────────────┘
           │                             │
           ▼                             ▼
┌───────────────────────┐    ┌───────────────────────┐
│       User            │◄───┤    FileHandler        │
│ (Base User Class)     │    │ (Data Persistence)    │
└──────────┬────────────┘    └──────────┬────────────┘
           │                             ▲
           ├──────────────┐              │
           ▼              ▼              │
┌───────────────────────┐ ┌───────────────────────┐
│       Donor          │ │      Receiver         │
│ (Donor Subclass)     │ │ (Receiver Subclass)   │
└──────────┬────────────┘ └──────────┬────────────┘
           │                         │
           ▼                         ▼
┌───────────────────────┐ ┌───────────────────────┐
│    DonorPanel        │ │    ReceiverPanel      │
│ (Donor Dashboard)    │ │ (Receiver Interface)  │
└──────────┬────────────┘ └──────────┬────────────┘
           │                         │
           ▼                         ▼
┌───────────────────────┐ ┌───────────────────────┐
│     Donation         │ │       Request         │
│ (Donation Model)     │ │ (Request Model)       │
└──────────┬────────────┘ └──────────┬────────────┘
           │                         │
           ▼                         ▼
┌───────────────────────┐ ┌───────────────────────┐
│   AdminPanel         │ │  UrgentRequestPanel   │
│ (Admin Dashboard)    │ │ (Priority System)     │
└───────────────────────┘ └───────────────────────┘



