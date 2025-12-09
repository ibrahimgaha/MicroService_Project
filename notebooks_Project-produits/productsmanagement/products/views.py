from rest_framework import viewsets
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status
from django.shortcuts import render, get_object_or_404, redirect
from django.http import JsonResponse
from .models import Notebook
from .serializers import NotebookSerializer

def notebook_list(request):
    notebooks = Notebook.objects.all()
    return render(request, 'products/notebook_list.html', {'notebooks': notebooks})

def notebook_detail(request, pk):
    notebook = get_object_or_404(Notebook, pk=pk)
    return render(request, 'products/notebook_detail.html', {'notebook': notebook})

def notebook_create(request):
    if request.method == 'POST':
        name = request.POST.get('name')
        price = request.POST.get('price')
        description = request.POST.get('description')
        image = request.FILES.get('image')
        Notebook.objects.create(name=name, price=price, description=description, image=image)
        return redirect('notebook_list')
    return render(request, 'products/notebook_form.html')

def notebook_update(request, pk):
    notebook = get_object_or_404(Notebook, pk=pk)
    if request.method == 'POST':
        notebook.name = request.POST.get('name')
        notebook.price = request.POST.get('price')
        notebook.description = request.POST.get('description')
        if request.FILES.get('image'):
            notebook.image = request.FILES.get('image')
        notebook.save()
        return redirect('notebook_detail', pk=notebook.pk)
    return render(request, 'products/notebook_form.html', {'notebook': notebook})

def notebook_delete(request, pk):
    notebook = get_object_or_404(Notebook, pk=pk)
    if request.method == 'POST':
        notebook.delete()
        return redirect('notebook_list')
    return render(request, 'products/notebook_confirm_delete.html', {'notebook': notebook})

# API views
class NotebookViewSet(viewsets.ModelViewSet):
    queryset = Notebook.objects.all()
    serializer_class = NotebookSerializer

@api_view(['GET'])
def health_check(request):
    return Response({"status": "healthy", "service": "notebook-management"}, status=status.HTTP_200_OK)

@api_view(['GET'])
def status_check(request):
    return Response({"status": "UP", "service": "ProductsManagement"}, status=status.HTTP_200_OK)

def status_page(request):
    return JsonResponse({"status": "UP", "service": "ProductsManagement"})
