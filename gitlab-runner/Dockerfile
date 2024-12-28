FROM gitlab/gitlab-runner:latest

# Установка kubectl
RUN curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl" \
    && chmod +x kubectl \
    && mv kubectl /usr/local/bin/kubectl

# Установка других инструментов, например helm (опционально)
RUN curl https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3 | bash

# Проверка установленных версий
RUN kubectl version --client && helm version
