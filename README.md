# App-ping

Service to listen for ping's from app with device location. 
- After getting ping, it registers it with user agent (device identificator)
- Used for research purposes
- App for getting location: https://github.com/marius-m/loca-ping
- Enables Firebase push notifications to periodically "wake-up" device for new location
  - Does not include `ws/src/main/resources/firebase-adminsdk-config.json`
  - Download it from: https://firebase.google.com/docs/cloud-messaging/server
