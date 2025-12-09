from django.urls import path, include
from rest_framework.routers import DefaultRouter
from . import views

router = DefaultRouter()
router.register(r'api/notebooks', views.NotebookViewSet)

urlpatterns = [
    # Web views
    path('', views.notebook_list, name='notebook_list'),
    path('notebook/<int:pk>/', views.notebook_detail, name='notebook_detail'),
    path('notebook/new/', views.notebook_create, name='notebook_create'),
    path('notebook/<int:pk>/edit/', views.notebook_update, name='notebook_update'),
    path('notebook/<int:pk>/delete/', views.notebook_delete, name='notebook_delete'),

    # API views
    path('', include(router.urls)),
    path('api/health/', views.health_check, name='health_check'),

    # Status endpoints for Eureka
    path('status/', views.status_page, name='status_page'),
    path('api/status/', views.status_check, name='status_check'),
]