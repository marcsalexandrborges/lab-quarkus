# Passos para implementação do Projeto - Parte 2

**Atenção:** utilize o vídeo abaixo como resumo técnico para acelerar a implementação da parte 2 do projeto

## Passos na Amazon Web Services (AWS)

1. Acessar a console da AWS. Na barra de pesquisas, digite IAM. Na seção Services, clique em IAM.
   - Clique em Users e em seguida Add users, insira o nome luxxy-covid-testing-system-pt-app1 e clique em Next para criar o usuário do tipo programmatic.
   - Após avançar, em Set permissions, clique no botão Attach existing policies directly.
   - Digite AmazonS3FullAccess em Search.
   - Selecione AmazonS3FullAccess.
   - Clique em Next.
   - Revise todos os detalhes.
   - Clique em Create user.

2. Passos para fazer o download da chave de acesso
   - Acesse o usuário luxxy-covid-testing-system-pt-app1.
   - Clique na aba Security credentials.
   - Navegue até a seção Access keys.
   - Clique em Create access key.
   - Selecione Command Line Interface (CLI) e I understand the above recommendation and want to proceed to create an access key.
   - Clique em Next.
   - Clique em Create access key.
   - Clique em Download .csv file.
   - Após o download finalizar, clique em Done.
   - Com o download feito, renomeie o .csv para luxxy-covid-testing-system-pt-app1.csv.

## Passos na Google Cloud Platform (GCP)

1. Navegue até a Cloud SQL instance e crie um novo usuário app com a senha welcome123456 no Cloud SQL MySQL database.
   - Se conecte ao Google Cloud Shell.
   - Faça o download dos arquivos da missão 2 diretamente para o Cloud Shell usando o comando `wget` abaixo:
     ```shell
     cd ~
     wget https://tcb-public-events.s3.amazonaws.com/icp/mission2.zip
     unzip mission2.zip
     ```
   - Verifique e copie o Public IP address de sua instância Cloud SQL em Overview.
   - Conecte ao MySQL DB em execução no Cloud SQL usando o Public IP address (assim que aparecer a janela para colocar a senha, insira welcome123456). Não esqueça de substituir o IP Público.
     ```shell
     mysql --host=<subtituir_public_ip_cloudsql> --port=3306 -u app -p
     ```
   - Após estar conectado ao banco de dados da instância, crie a tabela de produtos para testes.
     ```shell
     use dbcovidtesting;
     source ~/mission2/pt/db/create_table.sql
     show tables;
     exit;
     ```

2. Habilite a Cloud Build API através do Cloud Shell.
   ```shell
   gcloud services enable cloudbuild.googleapis.com


Faça o Build da Docker image e suba para o Google Container Registry. Por gentileza, substitua o <PROJECT_ID> com o My First Project ID.

GOOGLE_CLOUD_PROJECT_ID=$(gcloud config get-value project)
cd ~/mission2/pt/app
gcloud builds submit --tag gcr.io/$GOOGLE_CLOUD_PROJECT_ID/luxxy-covid-testing-system-app-pt

    Abra o Cloud Editor e edite o Kubernetes deployment file (luxxy-covid-testing-system.yaml) e atualize as variáveis conforme instruções fornecidas.

    Se conecte ao GKE (Google Kubernetes Engine) cluster via Console.

Passo 1
Passo 2

Faça o Deploy da aplicação COVID-19 Testing Status System no Cluster.

cd ~/mission2/pt/kubernetes
kubectl apply -f luxxy-covid-testing-system.yaml

Obtenha o IP Público e faça o teste da aplicação. Busque por GKE, clique em Workloads e em seguida Exposing Services endereço:porta.

Passo 1
Passo 2

Você deverá ver a aplicação rodando!

(Opcional) Baixe um exemplo de teste de COVID e adicione na aplicação

Clique no ícone abaixo para baixar o PDF.

Parabéns por concluir a parte 2 do projeto prático!
Anexo I - Destruindo o ambiente e começando novamente

Caso você tenha tido algum problema/erro e queira zerar o ambiente para começar novamente, siga o passo a passo abaixo para remover todo o ambiente MultiCloud:

    [Google Cloud] Deletar recursos Kubernetes

Passo 1
Passo 2

kubectl delete deployment luxxy-covid-testing-system 
kubectl delete service luxxy-covid-testing-system


    [Google Cloud] Deletar VPC Peering

    [AWS] Deletar arquivos dentro do S3

    [Google Cloud] Deletar recursos restantes com Terraform - Cloud Shell 

cd ~/mission1/pt/terraform/ 
terraform destroy 

Limpar o Cloud Shell na AWS e Google Cloud

AWS:

cd ~ 
rm -rf mission* 

Google Cloud:

cd ~ 
rm -rf mission* 
rm -rf .ssh 








