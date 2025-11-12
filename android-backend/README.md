# Symbiotic City - Android Backend API

Dedicated backend API for the Android application, sharing the PostgreSQL database with the web backend.

## Setup

1. Install dependencies:

```bash
npm install
```

2. Configure `.env` file with your database credentials

3. Start the server:

```bash
npm start
```

## API Endpoints

### Authentication

- POST `/api/auth/register` - Register new user
- POST `/api/auth/login` - Login user

### Projects

- GET `/api/projects` - Get all projects
- GET `/api/projects/:id` - Get project by ID
- POST `/api/projects` - Create project

### Events

- GET `/api/events` - Get all events
- GET `/api/events/:id` - Get event by ID
- POST `/api/events` - Create event

### Marketplace

- GET `/api/marketplace` - Get all items
- GET `/api/marketplace/:id` - Get item by ID
- POST `/api/marketplace` - Create item

### Profile

- GET `/api/profile` - Get user profile
- PUT `/api/profile` - Update profile

## Port

- Android Backend: **3002**
- Web Backend: **3001** (separate)
- Database: **Shared Supabase PostgreSQL**
