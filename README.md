# iron-msa
Iron's msa study repository<br>

<hr>

## 목적 :
- micro service architecture 이해

<hr>

## 각 서버의 역할 :
### config-server
각 시스템의 config 정보를 관리<br>
![image](https://github.com/nuts-playground/iron-msa/assets/76987021/dc0b4ce3-a431-4e4b-b79e-ae55d71f4da1)<br><hr>

### eureka-server
각 시스템의 로드밸런싱 및 장애조치<br>
![image](https://github.com/nuts-playground/iron-msa/assets/76987021/8aaedc16-b573-4eef-996b-399733b8f410)<br><hr>

### gateway-server
api 게이트웨이를 담당<br>
![image](https://github.com/nuts-playground/iron-msa/assets/76987021/4f779a34-0bf4-4614-bbd6-f541f00d759e)<br><hr>

### authentication-server
인증을 담당하며 토큰방식<br><hr>

### rms
예약시스템<br><hr>

# Docker
사용자 상황에 맞게 원하는 그룹별로 묶어서 관리할 수 있음.
수동으로 운영되고 관리되던 프로세스들을 간편하게 관리할 수 있음.

>데이터 or 프로그램을 격리시키는 기능.
>운영체제 통째로 격리하는 기능을 제공.
>도커를 이용하면 각 프로세스가 각 OS에서 운영되는 것처럼 독립적인 환경(컨테이너)에 격리하는 구조로 관리하는 기능을 제공.

Docker Engine이 동작하고 엔진 내부와 외부가 통신하는 방식.

## CI/CD

![image](https://github.com/nuts-playground/iron-msa/assets/76987021/929df29f-585f-4aee-8bca-e404f08ddc0f)

### Docker Image
Docker Container 를 실행하기 위해 여러 세팅들이 설정되어 있는 환경구성을 이미지에 담아놓은 것.

### Docker Container
분리된 독립서버.
추가 백업, 증설 등 가능.
Docker Image가 있어야 생성이 가능하다.
Docker Engine 만 있으면 다른 물리 서버에서도 Container는 동일하게 동작함.

### Docker Registry
#### registry
도커 이미지 저장소
도커 이미지를 registry에 업로드 해 두면 나중에 내려받아 컨테이너를 실행할 수 있음
백업 및 롤백 시 유용하게 사용.

 <<< 설치 및 동작 >>>
$ docker run -d --name registry -p 5000:5000 --restart always registry
 : registry 이미지를 내려받고 컨테이너까지 실행하는 명령어.
	- name registry : registry가 이름인 이미지 실행
	- p 5000:5000 : 외부 포트, 내부 포트 5000 설정
	- -restart always : 도커 재시작 시 항상 실행되도록 설정

#### registry-web
 : docker registry 관리 웹페이지

<< 설치 및 동작 >>
$ docker run -d -it -p 18050:8080 --name registry-web --link registry -e REGISTRY_URL=http://registry:5000/v2 --restart always -e REGISTRY_NAME=localhost:5000 hyper/docker-registry-web
 : 컨테이너 내부포트 8080으로 띄워지는 것을 외부포트 18050로 포트포워딩. --link 옵션은 컨테이너 내부 간 통신을 위해 추가한 옵션. registry라는 컨테이너와 통신한다는 뜻. -e 뒤에 REGISTRY_NAME과 REGISTRY_URL은 환경변수로 추가한 것인데, docker registry web을 띄우는 데 필요한 정보.

#### 이미지를 컨테이너로 만들기
$ docker commit -a "test" -m "commit test" rms localhost:5000/rms-image-test
 : -a 옵션은 제작자, -m 코멘트, 그 뒤에 rms는 컨테이너명, 마지막 localhost~rms-image-test는이미지명. 앞에 localhost:5000은 푸시할 registry URL 입력해야 함.
 
#### registry에 이미지 올리기
$ docker push localhost:5000/rms-image-test

#### registry에서 이미지 받기
$ docker pull localhost:5000/rms-image-test


### GitLab

#### GitLab 설치하기
$ docker run --detach --name gitlab --hostname localhost -p 80:80 --restart always --volume D:\study-workspace\springBoot-workspace\app\gitlab\config:/etc/gitlab --volume D:\study-workspace\springBoot-workspace\app\gitlab\logs:/var/log/gitlab --volume D:\study-workspace\springBoot-workspace\app\gitlab\data:/var/opt/gitlab gitlab/gitlab-ce
	hostme : gitlab에서 나중에 source clone할 때 사용되는 도메인 주소
	포트는 80으로 실행되며 외부 접근 포트도 80으로 설정함.
	volume 옵션을 통해 공유폴더를 mount함
	--name gitlab : 컨테이너명은 gitlab

$ docker logs -f gitlab
http 헬스체크하는 부분이 나올 때까지 기다릴 것.(대략 5~10분 정도 걸림)
설치가 완료되면 gitlab 사이트가 뜬다.

초기 로그인 계정 id는 root를 사용하며 root 비밀번호는 gitlab에서 초기값을 제공한다.

gitlab 컨테이너로 접근해서 비밀번호를 알아낸다.
$ docker exec -it gitlab /bin/bash
$ cat /etc/gitlab/initial_root_password

- initial_root_password 파일이 없어서 초기 비밀번호를 알 수 없을 경우 :
	실행 중인 프로세스를 확인하여 gitlab/gitlab-ce 이미지의 컨테이너 ID를 확인한다.
	$ docker ps
	root 비밀번호를 재설정한다.
	$ docker exec -it 26cdf286770a gitlab-rake "gitlab:password:reset\[root]"
	잠시 후 새 비밀번호를 입력하라는 메세지가 나오면 새 비밀번호를 설정하고 root로 로그인한다.

#### GitLab 사용
1. 로그인
2. 좌측 메뉴 -> Groups -> New Group -> Create group
	1. 알아서 설정
		1. group name : msa
		2. visibility level : private
3. Create new project -> Create blank project
	1. 알아서 설정
		1. project name : microservice
		2. visibility level : private
4. 프로젝트 업로드
	1. 깃이 연결되어있다면 디스커넥트
		1. 레포를 함께 삭제하지 않도록 주의할 것
	2. remote 설정
		1. origin입력 후 엔터
		2. clone with http url 복사해서 입력
5. 'CI/CD > Edit' 로 들어가야하는데, 없어서  'Build > Pipeline editor' 선택

```yml
variables: # 전역변수 설정. 자주 쓰는 값을 변수로 설정.

	# CIT_STRATEGY : Gitlab에서 소스를 가져오는 방법
	# fetch : 로컬에 복사해둔 작업본이 있으면 재활용(clone대신 fetch를 선택하는 편)
	# clone : 작업본을 처음부터 쭉 복사 (속도가 떨어짐.)
	# none : 하지않음.(필요한 구간만 fetch를 사용)
	GIT_STRATEGY: none
	
	# GIT_CHECKOUT : 소스를 내려받을지 여부
	# false : 내려받지 않음
    GIT_CHECKOUT: "false"

	# CONFIG_IMAGE_NAME : config 서버 도커 이미지명 변수로 지정
    CONFIG_IMAGE_NAME: config-server-image

	# CONFIG_CON_NAME : config 서버명 변수로 지정
    CONFIG_CON_NAME: config-server
  

# CI/CD 가 진행되는 단계를 지정
# 'clone -> build -> deploy' 단계로 진행
stages:
  - clone
  - build
  - deploy
  

# 소스를 내려받는 단계
clone:

  stage: clone

  variables:
      GIT_STRATEGY: fetch
      GIT_CHECKOUT: "true"

  # CI/CD를 진행할 gitlab runner 연동시 사용할 별칭 
  tags:
    - gitlab-runner

  # gitlab CI/CD 를 진행하기 위해 실행되는 명령어를 작성
  script:
    # windows 환경에서 진행하면 chcp 명령어를 통해 언어 셋을 맞춰줘야 함.
    - chcp 65001
  
# config-server를 빌드하기 위해 지정한 단계
configserver-build:

  stage: build

  tags:
    - gitlab-runner

  script:
    - echo $CONFIG_IMAGE_NAME
    - docker stop $CONFIG_CON_NAME || echo "none container"
    - docker rm $CONFIG_CON_NAME || echo "none container"
    - docker rmi $CONFIG_IMAGE_NAME || echo "none image"
    - docker ps -a
    - docker images
    - docker build ./config-server -t $CONFIG_IMAGE_NAME
    - docker images

configserver-deploy:

    stage: deploy

    tags:
      - gitlab-runner

    script:
      - docker run --network msa-network -d -p 8080:8080 --name $CONFIG_CON_NAME $CONFIG_IMAGE_NAME

```
6. https://docs.gitlab.com/runner/install/ 에 접속해서 gitlab runner 다운로드
	1. 다운받은 파일을 'app/gitlab-runner' 아래에 넣음.
7. gitlab runner를 서비스로 등록(window 실행 시 자동으로 실행하기 위함)
	1. 관리자 모드 cmd 열기
	2. app/gitlab-runner 위치로 이동.
	3.  $ gitlab-runner-windows-amd64.exe install
	4.  $ gitlab-runner-windows-amd64.exe start
8. Gitlab Runner 등록
	1. Settings > CI/CD > Runners > Expend
	2. registration token 값 복사 : GR1348941SaSshuxEh3isHHi_nEC9
		1. gitlab-runner가 gitlab에서 소스를 내려받기 위해 필요한 토큰
	3.  $ gitlab-runner-windows-amd64.exe register --url http://localhost/ --registration-token GR1348941SaSshuxEh3isHHi_nEC9
	4. `[http://localhost/]` : 엔터
	5. Enter the registration token : 엔터(위에 커맨드로 설정함.)
	6. `[DESKTOP-4L23M9F]` : gitlab-runner
	7. Enter tags for the runner (comma-separated) : gitlab-runner -> 태그명 gitlab-runner로 지정
	8. Enter optional maintenanace note for the runner : 엔터
	9. Enter an executor : shell, .... : shell -> shell 명령어를 통해 진행.
		1. shell 은 윈도우에서 pwsh명령어를 지원함
			1. pwsh : 마이크로소프트사에서 개발한 shell(powershell의 약자)
	10. $ gitlab-runner-windows-amd64.exe restart
	11. gitlab runner 페이지를 새로고침하거나 다시 접속하면 gitlab-runner 가 보인다.

- GitLab Runner :
	gitlab에 있는 repository에 저장된 소스를 내려받아서 CI/CD를 하기 위해 빌드하고 배포하는 작업을 제공하기 위해 사용하는 프로그램.
	보통 운영환경에 배포하기 위해 사용되는 서버에 설치.
	gitlab 서버와 운영서버 사이에 연결다리 역할을 할 수 있는 VPN 서버를 두고 그 서버에 gitlab runner를 설치해서 사용하는 게 좋을 것 같다고 함.
	이번에는 local pc window 환경에 설치함.

- .gitlab-ci.yml 최종파일 :
[[gitlab-ci.yml]]


# Prometheus
- 모니터링 도구가 필요한 이유 :
	MSA로 서비스하게 될 경우 서비스가 늘어날수록 관리해야 할 프로세스가 늘어나고 서버가 분리가 되면서 서버가 많아지게 되면 관리하고 모니터링해야 할 포인트가 많아진다.

## Prometheus 개념

![image](https://github.com/nuts-playground/iron-msa/assets/76987021/ee6499ac-5a76-4c12-8eea-f8e6cf159ed7)


- Jobs/Exporters : 모니터링 정보를 집계하는 프로그램.
	- 각 Exporter를 설치해서 Prometheus와 연동한다.
		Ex) Apache Kafka를 모니터링 -> Kafka 관련 Exporter를 설치
	- Exporter 프로그램에서 데이터를 집계해서 Prometheus Server로 전달한다.
	- 데이터 전달 시 HTTP 통신으로 GET 메서드 방식으로 Pull한다.
- Prometheus Server : 모든 모니터링 관련 수집 데이터들을 중앙 관리.
	- Prometheus Server 에 붙어있는 NODE : 하드디스크(수집된 데이터를 저장)
- Grafana / Prometheus web / API Client : 수집되어 저장된 데이터를 활용해서 특정한 목적으로 데이터를 가공해서 제공하는 프로그램.
	- Prometheus web : Prometheus를 설치하면서 제공되는 웹 프로그램.
		- 모니터링 집계된 데이터를 기본적으로 웹 화면에서 출력.
		- 가독성이 좋지 않음. 상세히 보기 좋지 않음.
	- Grafana : Prometheus web 에서 보기 좋지 않아 대신 활용
	- API Client : 개발자가 직접 개발한 특정 프로그램을 Prometheus와 연동해서 모니터링 데이터를 직접 조회하거나 가공하고 싶어서 연동한 프로그램.

## Prometheus 설치

### Prometheus 설정 파일 작성
>Prometheus는 실행될 때 `prometheus.yml`이란 이름의 파일을 로드해서 실행된다.
`prometheus.yml`파일에 어떤 걸 대상으로 모니터링할지에 대한 설정을 작성한다.

>임의로 경로는 /app/prometheus/prometheus.yml 

```yml
global:
  # 모니터링할 데이터 집계 주기
  scrap_interval: 15s
  # 규칙 평가 : 데이터의 규칙에 따라 담당자에게 alert를 보낼 수 있는데, 규칙 실행 주기
  evaluation_interval: 15s

scrape_configs:
  # 모니터링을 진행하는 job에 대한 이름
  - job_name: "prometheus"
  static_configs:
    # 모니터링 데이터들을 수집해 올 대상 서버 정보
    - targets: ["localhost:9090"]
```


### Prometheus 설치
1. cmd에 설치 명령어 입력
	$ docker run -dit -p 9090:9090 -v D:\study-workspace\springBoot-workspace\app\prometheus\prometheus.yml:\etc\prometheus\prometheus.yml --add-host host.docker.internal:host-gateway --restart=always --name prometheus prom/prometheus
		add-host host.docker.internal : 컨테이너에서 호스트 자원을 접근하기 위해 add-host라는 옵션을 통해 접근 가능하도록 설정.
2. prometheus 접속하여 확인
	localhost:9090 !!

#### 만난 오류

>위에 설치 커맨드 입력 시 경로를 제대로 설정하지 않으면 config file을 찾을 수 없다는 오류를 만날 수 있음
>경로 작성 시 역슬래시인 것을 잘 확인할 것.

### WMI exporter와 Prometheus 연동

#### WMI exporter

>prometheus와 연동되는 exporter의 한 종류.
>pc 자원에 대한 모니터링 데이터 수집을 위해 사용하는 수집 프로그램.
>Windows 에서 지원됨.
>**maxOS 나 Linux는 node exporter 라는 걸 설치해서 연동!**

#### WMI exporter 설치
1. WMI exporter 다운로드
	https://github.com/prometheus-community/windows_exporter/releases/
	`windows_exporter-0.25.1-amd64.msi`파일 다운로드하여 설치함.
2. 설치 후 실행 확인
	$ netstat -ano | findstr 9182
	설치가 되면 자동으로 9182포트로 실행됨.
3. localhost:9182/metrics 접속.
4.  exporter와 prometheus를 연동
5. /app/prometheus/prometheus.yml 파일 수정
	job_name windows 추가
	```yml
	global:  
		scrap_interval: 15s  
		evaluation_interval: 15s  
	  
	scrape_configs:  
	  - job_name: "prometheus"  
	    static_configs:  
	      - targets: ["localhost:9090"]
	  - job_name: "windows"
	    static_configs:
	      - targets: ["192.168.0.101:9182"]
	```
	job_name > static_configs > targets 에 127.0.0.1이나 0.0.0.0을 작성하면 prometheus 컨테이너에 대한 IP로 통신하기 때문에 실패할 수 있어서 PC이더넷에 대한 사설 IP를 작성해서 컨테이너에서 PC 사설 이더넷 IP로 통신할 수 있도록 설정함.
	내 경우에는 192.168.0.101
6. prometheus 재실행
	$ docker restart prometheus
7. prometheus 접속하여 연동 확인
	localhost:9090 > Status > Targets
	prometheus 아래에 windows 가 있고 State 에 UP 표시가 있으면 정상적으로 연동된 것.

>위처럼 job을 추가했지만 target으로 추가되지 않고 있음..~!!!!!!!!!!!!!!!!!!!!!!!!!!
>도저히 진행이 안돼서 일단 grafana로 넘어감.


## Grafana 설치

#### Grafana 설치하고 실행

1.  $ docker run -d -p 13000:3000 --restart=always --add-host host.docker.internal:host-gateway --name grafana grafana/grafana
	내부 접근 포트는 3000, 외부 접근 포트는 13000. --add-host 옵션을 설정한 이유는 prometheus에 접근하기 위함. 이 옵션이 아니더라도 같은 네트워크를 사용하면 접근 가능함.
2. 컨테이너 실행 후 localhost:13000/login 으로 접속
3. admin/admin 로그인
4. 초기 비밀번호 설정. 저는 다시 admin으로 설정함.
5. 좌측 상단 설정아이콘 > Administration > Data sources 를 찾으라고 하는데 UI가 바뀐 탓인지 안 보여서 그냥 Data sources 검색함.
6. Prometheus 선택 후 보이는 화면은 Grafana와 Prometheus 연동 정보를 입력하는 화면.
7. 다른 부분은 놔두고 HTTP > URL 부분만 입력.
	PC에서 사용하고 있는 사설 IP
	URL : http://192.168.0.101:9090
8. Save & Test 클릭
9. 성공적으로 어쩌구라는 화면이 뜨고 'Explore view'클릭하면 Prometheus에서 수집된 데이터를 보는 화면으로 이동한다.
하지만 내 경우엔 수집되지 않는 관계로 아무것도 보이지 않았다
