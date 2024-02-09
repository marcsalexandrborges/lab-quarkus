# Passos para implementação do Projeto - Parte 1

**Atenção:** utilize o vídeo abaixo como resumo técnico para acelerar a implementação da parte 1 do projeto

## Passos na Amazon Web Services (AWS)

1. Criando o usuário terraform-pt-1 usando o serviço IAM
   - Acesse a console da AWS ([https://aws.amazon.com](https://aws.amazon.com)) e logue com sua nova criada. Na barra de pesquisas, digite IAM. Na seção Services, clique em IAM.
   - Clique em Users e em seguida Add users, insira o nome terraform-pt-1 e clique em Next para criar o usuário do tipo programmatic.
   - Após avançar, em Set permissions, clique no botão Attach existing policies directly.
   - Digite AmazonS3FullAccess em Search.
   - Selecione AmazonS3FullAccess.
   - Clique em Next.
   - Revise todos os detalhes.
   - Clique em Create user.

2. Criando o Access Key (Chave de Acesso) para o usuário terraform-pt-1 usando o serviço IAM
   - Acesse o usuário terraform-pt-1.
   - Clique na aba Security credentials.
   - Navegue até a seção Access keys.
   - Clique em Create access key.
   - Selecione Command Line Interface (CLI) e I understand the above recommendation and want to proceed to create an access key.
   - Clique em Next.
   - Clique em Create access key.
   - Clique em Download .csv file.
   - Após o download finalizar, clique em Done.
   - Com o download feito, renomeie o .csv para key.csv.

## Passos na Google Cloud Platform (GCP)

1. Preparando o ambiente para executar o Terraform
   - Acesse a Console da Google Cloud ([https://console.cloud.google.com/](https://console.cloud.google.com/)) e logue com sua nova criada.
   - Abra o Cloud Shell.
   - Baixe o arquivo mission1.zip no Google Cloud shell usando o comando `wget`: `wget https://tcb-public-events.s3.amazonaws.com/icp/mission1.zip`.
   - Faça o upload do arquivo key.csv para o Cloud Shell usando o browser.
   - Verifique se os arquivos mission1.zip e key.csv estão na pasta no Cloud Shell usando o comando abaixo: `ls`.
   - Execute os comandos de preparação dos arquivos:
     ```shell
     unzip mission1.zip
     mv key.csv mission1/pt
     cd mission1/pt
     chmod +x *.sh
     ```
   - Execute os comandos abaixo para preparar o ambiente da AWS e GCP:
     ```shell
     mkdir -p ~/.aws/
     touch ~/.aws/credentials_multiclouddeploy
     ./aws_set_credentials.sh key.csv
     GOOGLE_CLOUD_PROJECT_ID=$(gcloud config get-value project)
     gcloud config set project $GOOGLE_CLOUD_PROJECT_ID
     ./gcp_set_project.sh
     gcloud services enable containerregistry.googleapis.com
     gcloud services enable container.googleapis.com
     gcloud services enable sqladmin.googleapis.com
     gcloud services enable cloudresourcemanager.googleapis.com
     gcloud services enable serviceusage.googleapis.com
     gcloud services enable compute.googleapis.com
     gcloud services enable servicenetworking.googleapis.com --project=$GOOGLE_CLOUD_PROJECT_ID
     ```

2. Executando o Terraform para provisionar a infraestrutura MultiCloud na AWS e Google Cloud
   - Execute os seguintes comandos para provisionar os recursos de infraestrutura:
     ```shell
     cd ~/mission1/pt/terraform/
     terraform init
     terraform plan
     terraform apply
     ```
   - **Atenção:** O processo de provisionamento pode levar entre 15 a 25 minutos para finalizar. Mantenha o CloudShell aberto durante o processo. Caso seja desconectado, clique em Reconectar quando a sessão expirar (a sessão expira após 5 minutos de inatividade por padrão).

## Anexo I - Destruindo o ambiente e começando novamente

Caso você tenha tido algum problema/erro e queira zerar o ambiente para começar novamente, siga o passo a passo abaixo para remover todo o ambiente MultiCloud:

[Google Cloud] Deletar VPC Peering

[Google Cloud] Deletar recursos restantes com Terraform - Cloud Shell
```shell
cd ~/mission1/pt/terraform/
terraform destroy

Limpar o Cloud Shell na AWS e Google Cloud:

AWS:

cd ~
rm -rf mission*

Google Cloud:

cd ~
rm -rf mission*
rm -rf .ssh

Dicas de Segurança

Para ambientes de produção, é recomendado utilizar apenas a Rede Privada para o acesso ao banco de dados.
Nunca fornecer acesso à rede pública (0.0.0.0/0) para os banco de dados de produção.

Chegando até aqui, você concluiu a implementação da primeira parte do Projeto Hands-on e fez a implementação dos recursos em um ambiente MultiCloud (AWS e Google Cloud) utilizando o Terraform!

Parabéns!



