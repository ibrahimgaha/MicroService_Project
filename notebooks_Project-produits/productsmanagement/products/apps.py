from django.apps import AppConfig
from django.conf import settings
import os
import threading
import py_eureka_client.eureka_client as eureka_client


class ProductsConfig(AppConfig):
    default_auto_field = 'django.db.models.BigAutoField'
    name = 'products'

    def ready(self):
        if os.environ.get("RUN_MAIN") != "true":
            return

        def register():
            try:
                eureka_client.init(
                    eureka_server=settings.EUREKA_CLIENT["EUREKA_SERVER"],
                    app_name=settings.EUREKA_CLIENT["APP_NAME"],
                    instance_host=settings.EUREKA_CLIENT["INSTANCE_HOST"],
                    instance_port=int(settings.EUREKA_CLIENT["INSTANCE_PORT"]),
                    instance_id=settings.EUREKA_CLIENT["INSTANCE_ID"],
                    health_check_url=f"http://{settings.EUREKA_CLIENT['INSTANCE_HOST']}:{settings.EUREKA_CLIENT['INSTANCE_PORT']}/health/",
                    status_page_url=f"http://{settings.EUREKA_CLIENT['INSTANCE_HOST']}:{settings.EUREKA_CLIENT['INSTANCE_PORT']}/health/",
                )
                print("✅ Django product-service registered in Eureka")
            except Exception as e:
                print(f"❌ Eureka registration failed: {e}")

        threading.Thread(target=register, daemon=True).start()
