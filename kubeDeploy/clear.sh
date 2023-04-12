kubectl delete -f mysql-root-credentials.yml
kubectl delete -f mysql-credentials.yml
kubectl delete -f mysql-configmap.yml
kubectl delete -f mysql-deployment.yml
kubectl delete -f backend-deployment.yml
kubectl delete -f frontend-deployment.yml