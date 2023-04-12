kubectl apply -f mysql-root-credentials.yml
kubectl apply -f mysql-credentials.yml
kubectl apply -f mysql-configmap.yml
kubectl apply -f mysql-deployment.yml
kubectl apply -f backend-deployment.yml
kubectl apply -f frontend-deployment.yml