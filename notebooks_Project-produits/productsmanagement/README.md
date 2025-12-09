# Notebook Management Microservice

A Django-based microservice for managing notebook products with Eureka service discovery integration.

## Features

- **CRUD Operations**: Complete Create, Read, Update, Delete operations for notebooks
- **REST API**: RESTful API endpoints for integration with other services
- **Admin Interface**: Django admin panel for easy management
- **Eureka Integration**: Automatic service registration with Eureka server
- **Health Checks**: Health check endpoint for monitoring
- **Docker Support**: Containerized deployment with Docker and Docker Compose

## API Endpoints

### REST API
- `GET /api/notebooks/` - List all notebooks
- `POST /api/notebooks/` - Create a new notebook
- `GET /api/notebooks/{id}/` - Get notebook details
- `PUT /api/notebooks/{id}/` - Update notebook
- `DELETE /api/notebooks/{id}/` - Delete notebook
- `GET /api/health/` - Health check

### Web Interface
- `/` - Notebook list
- `/notebook/{id}/` - Notebook details
- `/notebook/new/` - Create notebook
- `/notebook/{id}/edit/` - Edit notebook
- `/notebook/{id}/delete/` - Delete notebook
- `/admin/` - Django admin panel

## Environment Variables

Configure the following environment variables in your `.env` file:

```env
# Django settings
SECRET_KEY=your-secret-key
DEBUG=True
ALLOWED_HOSTS=localhost,127.0.0.1

# Database
DATABASE_NAME=db.sqlite3

# Eureka settings
EUREKA_SERVER=http://localhost:8761/eureka/
SERVICE_NAME=notebook-management
SERVICE_HOST=localhost
SERVICE_PORT=8000

# Microservice settings
MICROSERVICE_NAME=Notebook Management Service
MICROSERVICE_VERSION=1.0.0
```

## Running Locally

1. Install dependencies:
```bash
pip install -r requirements.txt
```

2. Run migrations:
```bash
python manage.py migrate
```

3. Start the server:
```bash
python manage.py runserver
```

## Docker Deployment

1. Build and run with Docker Compose:
```bash
docker-compose up --build
```

This will start both the notebook management service and Eureka server.

## Eureka Integration

The service automatically registers with Eureka on startup and deregisters on shutdown. The service is discoverable at:
- **Service Name**: `notebook-management`
- **Port**: `8000`

## Health Check

The service provides a health check endpoint at `/api/health/` that returns:
```json
{
  "status": "healthy",
  "service": "notebook-management"
}
```

## Development

To create a superuser for admin access:
```bash
python manage.py createsuperuser
```

## Integration with Other Microservices

Other services can discover and communicate with this service through Eureka. Use the service name `notebook-management` to make requests to the API endpoints.