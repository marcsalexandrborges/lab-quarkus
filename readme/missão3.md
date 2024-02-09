
# Passos para implementação do Projeto - Parte 3

‼️ **Atenção:** utilize o vídeo abaixo como resumo técnico para acelerar a implementação da parte 3 do projeto

[ICP_PT_MISSAO3_COMPLEMENTO.mp4](link_para_o_video)

## Passos na Google Cloud Platform (Migração do Banco de Dados MySQL)

- Conectar ao Google Cloud Shell
- Download o dump do banco de dados usando o comando wget
  ```shell
  cd ~
  wget <https://tcb-public-events.s3.amazonaws.com/icp/mission3.zip>
  unzip mission3.zip

Conecte ao MySQL DB em execução no Cloud SQL usando o Public IP address (assim que aparecer a janela para colocar a senha, insira welcome123456). Não esqueça de substituir o IP Público.

mysql --host=<subtituir_public_ip_cloudsql> --port=3306 -u app -p

Importar o dump do banco de dados no Cloud SQL

use dbcovidtesting;
source ~/mission3/pt/db/db_dump.sql

Checar se os dados foram importados com sucesso

select * from records;
exit;

Passos na Amazon Web Services (Migração dos arquivos PDF)

    Conectar no AWS Cloud Shell
    Download dos arquivos PDF (Comprovante de teste negativo escaneado em PDF)


wget <https://tcb-public-events.s3.amazonaws.com/icp/mission3.zip>
unzip mission3.zip

Sincronizar os arquivos PDF com o seu bucket criado no AWS S3 usado para o COVID-19 Testing Status System. Altere o nome do bucket para o seu bucket.

cd mission3/pt/pdf_files
aws s3 sync . s3://luxxy-covid-testing-system-pdf-pt-xxxx

    Testar a aplicação. Ao testar a aplicação e navegar na opção "Ver registros" você deverá ser capaz de visualizar os dados importados!

Parabéns! Você migrou uma aplicação e seu banco de dados do "on-premises" para uma Arquitetura MultiCloud!

Anexo I - Destruindo o ambiente

Após concluir o projeto prático e coletar as evidências de implementação, siga o passo a passo abaixo para remover todo o ambiente MultiCloud:

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








