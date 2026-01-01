from django.apps import AppConfig
from django.conf import settings
import threading
import requests
import asyncio
from py_eureka_client.eureka_client import EurekaClient


class ProductsConfig(AppConfig):
    default_auto_field = 'django.db.models.BigAutoField'
    name = 'products'

    def ready(self):
        """
        Initialise le client Eureka et enregistre le service Django au démarrage.
        """
        eureka_server = settings.EUREKA_CLIENT['EUREKA_SERVER']
        app_name = settings.EUREKA_CLIENT['APP_NAME']
        instance_host = settings.EUREKA_CLIENT['INSTANCE_HOST']
        instance_port = settings.EUREKA_CLIENT['INSTANCE_PORT']
        instance_id = settings.EUREKA_CLIENT['INSTANCE_ID']

        eureka_client = EurekaClient(
            eureka_server=eureka_server,
            app_name=app_name,
            instance_host=instance_host,
            instance_port=instance_port,
            instance_id=instance_id,
        )

        def register_with_eureka():
            try:
                # Données de l'instance pour l'enregistrement manuel
                instance_data = {
                    "instance": {
                        "instanceId": instance_id,
                        "hostName": instance_host,
                        "app": app_name,
                        "ipAddr": instance_host,
                        "status": "UP",
                        "port": {"$": instance_port, "@enabled": "true"},
                        "securePort": {"$": 443, "@enabled": "false"},
                        "healthCheckUrl": f"http://{instance_host}:{instance_port}/api/health/",
                        "statusPageUrl": f"http://{instance_host}:{instance_port}/status/",
                        "homePageUrl": f"http://{instance_host}:{instance_port}/",
                        "dataCenterInfo": {
                            "@class": "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo",
                            "name": "MyOwn"
                        }
                    }
                }

                # Enregistrement manuel dans Eureka
                response = requests.post(
                    f"{eureka_server}/apps/{app_name}",
                    json=instance_data,
                    headers={"Content-Type": "application/json"}
                )

                if response.status_code == 204:
                    print("Django service registered with Eureka successfully (manual)")
                else:
                    # Sinon utilisation de la bibliothèque Eureka
                    asyncio.run(eureka_client.register())
                    print("Django service registered with Eureka successfully (library)")

            except Exception as e:
                print(f"Failed to register Django service with Eureka: {e}")

        # Lancer l'enregistrement dans un thread séparé pour ne pas bloquer Django
        thread = threading.Thread(target=register_with_eureka)
        thread.daemon = True
        thread.start()