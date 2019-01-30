package com.example.hk.eip.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hk.eip.R;
import com.example.hk.eip.database.DbOpenHelper;

public class MainActivity extends Activity {
    private static final String TAG = "EIP_001";

    private TextView[] main_tv_wordCount;

    private DbOpenHelper mDbOpenHelper;
    private Cursor main_Cursor;
    private SharedPreferences prefs;

    private int wordCount;
    private int wordUncheckedCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_tv_wordCount = new TextView[3];
        main_tv_wordCount[0] = (TextView) findViewById(R.id.main_word_count);
        main_tv_wordCount[1] = (TextView) findViewById(R.id.main_word_chk);
        main_tv_wordCount[2] = (TextView) findViewById(R.id.main_word_unchk);

        // Shares Preference 호출
        prefs = getSharedPreferences("EasyWordBook", MODE_PRIVATE);
        boolean onDB = prefs.getBoolean("key_firstStart", false);
        Log.i("onClick", "" + onDB);

        mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();

        // 최초 DB Create
        if (!onDB) {
            // 2018 상반기
            mDbOpenHelper.insertColumn("클릭 농장(click farm, 가짜 클릭 농장)",
                    "인터넷에서 특정 상품의 조회 수, 앱 다운로드 수 등을 조작할 목적으로 가짜 클릭을 대량으로 생산하는 행위나 생산하는 곳." +
                            " 인건비가 저렴한 개발도상국의 근로자들을 이용하여 노동 착취의 문제도 불러일으키고 있다.", 0);
            mDbOpenHelper.insertColumn("TEE(Trusted Execution Environment)",
                    "프로세서 내에 일반 영역(normal area)과 다른 보안 영역(secure area)을 제공함으로서," +
                            " 칩 설계회사인 ARM(Advanced RISC Machine)에서 개발한 기술인 트러스트존(TrustZone)이 대 표적인 신뢰 실행 환경(TEE)으로 꼽힌다.", 0);
            mDbOpenHelper.insertColumn("TPM(Trusted Platform Module)",
                    "암호화된 키·패스워드·디지털 인증서와 같이 보안을 필요로 하는 중요한 데이터를 하드웨어적으로 분리된 안전한 공간에 저장하여 키의 관리나 암호화 처리" +
                            " 등을 해당 보안 장치 내부에서만 처리하도록 하는 강력한 보안 환경을 제공하는 모듈.", 0);
            mDbOpenHelper.insertColumn("비식별화(de-identification)",
                    "개인을 식별할 수 있는 요소들, 즉 개인정보들을 식별할 수 없도록 변환하는 과정 또는 방법. 이와 비슷한 용어로 " +
                            "익명화(Anonymization)가 있으나, 이는 다르게 이후 재식별화가 불가능하 게 데이터를 변환한다는 점에서 차이가 있다.", 0);
            mDbOpenHelper.insertColumn("준식별자(quasi-identifier)",
                    "정보를 서로 결합하여 개인을 식별할 수 있는 개인 정보 또는 민감 정보(sensitive information)", 0);
            mDbOpenHelper.insertColumn("재식별화(re-identification)",
                    "개인을 식별할 수 없도록 변환한 것을 다시 식별할 수 있도록 다른 정보와 조합·분석·처리하여 개인을 식별해내는 과정 또는 방법. " +
                            "국제표준화기구 ISO/IEC 20889 표준에서는 '데이터로부터 원래의 정보주체의 비식별화 데이터 집합을 귀속시키는 과정' 이라 정의", 0);
            mDbOpenHelper.insertColumn("프라이버시 마스킹(privacy masking)",
                    "대량의 데이터가 활용되는 시대에서 개인을 식별하는 데 단초가 될 수 있는 이름, 사진, 주민번호, 주소" +
                            " 등 의 민감한 정보들을 보이지 않도록 처리하는 기술", 0);
            mDbOpenHelper.insertColumn("스마트 계약(Smart Contract)",
                    "1996년 컴퓨터 공학자인 닉 사보(Nick Szabo)가 제시한 개념으로, '디지털 형식으로 명시된 일련의 약속으로, 당사자가 약속을 이행하는 프로토콜을 포함한다.'" +
                            "라고 언급했다. 관측 가능성(Observability), 검증 가능성(Verifiability), 비밀성(Privity), 강제 가능성(Enforceability)을 지녀야 한다.", 0);
            mDbOpenHelper.insertColumn("DLT(Distributed Ledger Technology)",
                    "중앙 관리자나 중앙 데이터 저장소가 존재하지 않고, P2P망 내의 참여자들에게 모든 거래 목록이 분산 저장되어," +
                            " 거래가 발생할 때마다 지속적으로 갱신되는 디지털 원장을 의미", 0);
            mDbOpenHelper.insertColumn("암호화폐(cryptocurrency)",
                    "온라인 거래에서 안전하게 사용될 수 있도록 해시함수를 이용한 암호화 기술(cryptography)이 적용된 전자 화폐", 0);
            mDbOpenHelper.insertColumn("양자 암호(quantum cryptography)",
                    "양자 역학의 특성을 활용한 암호 기술. 수학적 복잡성을 기반으로 하는 기존의 암호체계와는 달리 양자(quantum)의 특성을 기반으로 한" +
                            "다. 양자는 복사할 수 없고 원래의 상태로 되돌릴 수 없는 특성을 가지는데, 이는 송신자와 수신자 이외의 제 3자가 도청을 위해 측정하는 순간 양자 " +
                            "상태를 변화시켜 수신자가 도청 시도를 파악할 수 있다.", 0);
            mDbOpenHelper.insertColumn("OOK(On-Off Keying)",
                    "디지털 데이터를 전송하기 위해 반송파(carrier wave)를 단속(on-off)하는 방식으로 변조한다. 0과 1만을 사용하는 2 레벨(level) 변조 방식", 0);
            mDbOpenHelper.insertColumn("가상 현실 위치 추적(virtual reality positional tracking)",
                    "HMD(Head Mounted Display), 콘트롤러, 주변 기기 등의 하드웨어와 소프트웨어의 조합을 통해 자유도(DOF, Degrees Of Freedom)" +
                            "를 측정하여 동작 변화를 추적하는 기술로 가상현실(VR)에 필수적인 기술", 0);
            mDbOpenHelper.insertColumn("인포그래픽스(Infographics, 인포그래픽, 정보그림)",
                    "보는 사람이 쉽고 빠르게 이해할 수 있도록 정보·자료·지식을 시각적으로 표현하는 것으로 인포메이션 그래픽스 (information graphics)의 줄임말." +
                            " 복잡한 정보를 빠르고 명확하게 설명해야 하는 지도나 기술 문서, 특정 광고 등에서 주로 사용되며, 달력 이나 텔레비전 프로그램 편성표 등도 인포그래픽스에 포함된다.", 0);
            mDbOpenHelper.insertColumn("패스트 데이터(fast data)",
                    "생성과 동시에 즉시 처리되는 데이터를 의미하는 용어로, 주로 빅데이터(big data)와 비교된다. 사용되는 일반적인 사례로는 운송 분야에서 화물의 출하, 경유, 도착 등을 실시간으로 확인" +
                            "할 수 있는 화물 추적 시스템과, 의료 분야에서 환자의 건강 상태를 확인하는 각종 의료기기들의 데이터, 금융 분야에서 실시간으로 표시되는 주식 지표 등을 들 수 있다.", 0);
            mDbOpenHelper.insertColumn("시선 추적(eye tracking)",
                    "센서를 통해 눈의 움직임이나 시선의 위치를 추적하는 기술", 0);
            mDbOpenHelper.insertColumn("신경 모방 칩(neuromorphic chip)",
                    "인간의 뇌신경 구조를 모방한 반도체 소자로, 인간의 사고 과정과 유사하게 정보를 처리하는 것이 가능하여 딥 러닝(deep learning) 등의 인공지능 " +
                            "기능을 구현하는 것이 가능하다.", 0);
            mDbOpenHelper.insertColumn("FFNet(Feed-Forward Neural Network, 순방향 신경망, 전방향 신경망)",
                    "인공 신경망은 기계학습(machine learning)과 인지과학(cognitive science)에서 인간이나 동물의 중추신경계에서 영감을 얻어 연구되고" +
                            " 있는 통계학적인 학습 알고리즘으로, 이 중 순환이나 루프가 없이 한 방향으로만 진행 하는 신경망을 의미한다.", 0);
            mDbOpenHelper.insertColumn("RNN(Recurrent Neural Network)",
                    "기준 시점(t)과 다음 시점(t+1)에 네트워크를 연결하여 시계열성을 갖는 데이터들, 즉 음악이나 동영상 등의 시간의 흐름에 따라 변화하는 데이터 또는 음성 인식," +
                            " 문자열 같이 순차적인 정보가 담긴 데이터를 다룰 수 있는 모델", 0);
            mDbOpenHelper.insertColumn("DNN(Deep Neural Network)",
                    "은닉층이 다중으로 이루어진 신경망", 0);
            mDbOpenHelper.insertColumn("CNN(Convolutional Neural Network)",
                    "여러 개의 은닉층으로 이루어진 심층 신경망의 한 종류로, 2차원 구조의 데이터 처리, 영상 및 음성 분석, 객체 탐지 등" +
                            " 여러 분야에 활용되는 심층 신경망의 대표적인 모델 중 하나이다.", 0);
            mDbOpenHelper.insertColumn("CC(Cognitive Computing)",
                    "인간 두뇌의 인지와 사고를 모방하여 학습능력이나 분석능력을 향상시켜 다양한 환경에 대응하기 위한 모델. 전통적인 인공지능의 개념을 포괄한다. 주로 음성 인식, " +
                            "감정 분석, 얼굴 인식, 위험 평가, 사기 탐지 등의 기능을 구현하는데 사용되며, 대표적 인 플랫폼 제공사로는 IBM의 왓슨(Watson)이 있다.", 0);
            mDbOpenHelper.insertColumn("MMORPG(Massively Multiplayer Online Role-Playing Game)",
                    "온라인을 통한 대규모 다중 이용자(MMO, Massively Multiplayer Online)와 역할 게임을 의미하는 롤플레잉 게임(RPG, Role Playing Game)의 합성어로," +
                            " 수천 이상의 플레이어가 인터넷을 통해 같은 게임에 접속하여 각자 선택한 캐릭터의 역할을 혼자 수행하거나 협력하며 즐기는 게임을 의미", 0);
            mDbOpenHelper.insertColumn("인터넷 밈(internet meme)",
                    "인터넷 환경에서 인터넷 문화의 확산을 의미. 주로 타인에게 재미를 주기 위한 것이 대부분이나 아이스 버킷 챌린지(Ice Bucket Challenge), 미투(#MeToo)와 같이 기부나 " +
                            "사회 문화 활동을 위해 사용되는 경우도 존재한다.", 0);
            mDbOpenHelper.insertColumn("D-TDD(Dynamic Time Division Duplexing)",
                    "동일 주파수 대역에서 시간적으로 상향 링크(uplink)와 하향 링크(downlink)를 교대로 배정하는 시분할 송수신(TDD, Time Division Duplexing)에서," +
                            " 각 링크의 순간적인 트래픽 부하와 주변 셀(cell)들의 간섭 상황을 고려하여 타임 슬롯(time slot)을 유동적으로 할당하는 방식", 0);
            mDbOpenHelper.insertColumn("C-DRX(Connected mode Discontinuous Reception)",
                    "3GPP에서 공표한 Release 8버전에서 정의하고 있는 기술로, LTE망에서 스마트폰이나 태블릿 등의 기기들이 데이터를 사용하지 않는 시간동안 무선 주파수 송수신 기능을" +
                            " 일시적으로 중지시켜 배터리를 효율적으로 사용할 수 있게 해주는 망 기술", 0);
            mDbOpenHelper.insertColumn("mMTC(massive Machine Type Communications)",
                    "일정 구역 안에 적게는 수십 개에서 많게는 수십만 개의 기기에 대해 사물 인터넷(IoT) 서비스를 제공하는 것", 0);
            mDbOpenHelper.insertColumn("eMBB(enhanced Mobile BroadBand)",
                    "사용자가 5G 통신망을 사용할 때 체감되는 초광대역 서비스에 대해 정의한 것", 0);
            mDbOpenHelper.insertColumn("URLLC(Ultra-Reliable and Low Latency Communications)",
                    "사용자가 5G 이동통신에서 누리는 높은 신뢰도(ultra-reliable)와 낮은 지연 시간(low latency)의 서비스들", 0);
            mDbOpenHelper.insertColumn("네트워크 슬라이싱 (network slicing)",
                    "네트워크에서 하나의 물리적인 코어 네트워크 인프라를 독립된 다수의 가상 네트워크로 분리하여 각각의 네트워크를 통해 다양한 고객 맞춤형 서비스를 제공하는 것을 " +
                            "목적으로 하는 네트워크 기술", 0);
            mDbOpenHelper.insertColumn("SDN(Software-Defined Networking)",
                    "네트워크에서 제어부와 데이터 전달부를 분리하여 관리자가 소프트웨어를 통해 네트워크를 효율적으로 제어·관리할 수 있게 하는 기술",0);
            mDbOpenHelper.insertColumn("NFV(Network Functions Virtualization)",
                    "네트워크 구성에 필요한 하드웨어들을 소프트웨어적으로 구현하여 장비를 줄이는 기술", 0);
            mDbOpenHelper.insertColumn("IMT-2020(International Mobile Telecommunications-2020)",
                    "2015년 국제 전기 통신 연합(ITU, International Telecommunication Union)에서 선정한 5세대 이동 통신(5G, Fifth Generation)의 공식 명", 0);
            mDbOpenHelper.insertColumn("인터넷 전문 은행(Internet only Bank, Direct Bank)",
                    "오프라인 지점을 토대로 하는 기존의 은행들과는 달리, 대부분의 금융 서비스를 인터넷 상 에서 제공하는 은행", 0);
            mDbOpenHelper.insertColumn("가상 화폐(virtual currency, virtual money)",
                    "지폐나 동전과 같은 실물이 없이 온라인 네트워크 상에서 발행되어 온라인 또는 오프라인에서 사용할 수 있는 디지털 화폐 또는 전자 화폐", 0);
            mDbOpenHelper.insertColumn("WMC(payment based Wireless Magnetic Communication)",
                    "스마트폰과 같은 모바일 기기에서 마그네틱 신호(자기장)를 발생시켜 마그네틱 카드를 결제할 수 있는 결제 단말 기에 근접시키면 결제되는 방식", 0);
            mDbOpenHelper.insertColumn("VPP(Virtual Power Plant)",
                    "분산되어 있는 소규모의 태양광, 수력, 풍력 등의 발전기나, 천연 가스를 사용하는 연소 왕복 엔진과 같은 신재 생 에너지원(Renewable Energy Source) 발전설비," +
                            " 축전지·연료전지 등의 에너지 저장 장치(ESS, Energy Storage System)들을 클라우드 기반의 소프트웨어로 통합·제어하여 전력의 공급과 수요를 유연하게 관리할 수 있는 가상의 발전소", 0);
            mDbOpenHelper.insertColumn("POW(Proof-Of-Work)",
                    " P2P(Peer-to-Peer) 네트워크에 참여한 당사자들 간에 수행한 연산 작업의 신뢰성을 보장받기 위해 검증하는 방식이다." +
                            " 네트워크에 참여하는 모든 사용자가 데이터를 분산 저장하여 처리하는 기술인 블록체인에서도 이용된다.", 0);
            mDbOpenHelper.insertColumn("지상파 UHD 방송 표준(Terrestrial Ultra High Definition broadcasting standard)",
                    " 한국정보통신기술협회에서 국내의 지상파 UHD 방송 활성화를 위해 미국의 디지털 TV 방송 " +
                            "표준인 ATSC 3.0을 기반으로 하여 국내 방송 환경에 맞춰 2016년 12월 27일 제정하였다. 주요 기술로는 고효율 비디오 코딩(HEVC, High Efficiency Video Coding)," +
                            " MPEG-H 3D 오디오, 계층 분할 다중화(LDM, Layered Division Multiplexing) 등이 있다", 0);
            mDbOpenHelper.insertColumn("10.2 채널 음향(10.2 channel audio)",
                    "한국전자통신연구원과 삼성전자가 공동 개발한 음향 시스템으로, 청취자를 중심으로 10개의 독립된 스피커와 가청 주파수 100Hz 이하의 저음을 내는 2개의 서브우퍼가 기존의 5.1 채널 " +
                            "음향에 비해 보다 향상된 몰입감과 현장감을 제공", 0);
            mDbOpenHelper.insertColumn("컴패니언 스크린(companion screen)",
                    "TV 방송 시청 시 방송 내용을 공유하며 추가적인 기능을 수행할 수 있는 스마트폰, 태블릿PC 등을 의미하며 " +
                            "세컨드 스크린(second screen)이라고도 불린다.", 0);
            mDbOpenHelper.insertColumn("스마트 수어 방송(smart sign language broadcasting)",
                    "방송 영상은 방송망으로, 수어(수화 언어) 영상은 인터넷망으로 송신한 후 수신기에서 두 영상을 동기화시켜 한 화면에 재생한다. 이로 인해 " +
                            "수신기에서는 방송 영상과 별도로 수어 영상의 표시 여부, 크기·위치를 제어할 수 있다.", 0);
            mDbOpenHelper.insertColumn("IBB(Integrated Broadcast Broadband)",
                    "HTML5 웹 표준을 기반으로 하여 지상파 방송과 인터넷 서비스를 융합한 통합 방송 서비스", 0);
            mDbOpenHelper.insertColumn("CCA(Clear Channel Assessment)",
                    "WLAN 또는 Wi-Fi 채널의 사용 가능 여부를 평가하는 기술", 0);
            mDbOpenHelper.insertColumn("와이파이 대역 조정 기술(Wi-Fi band steering)",
                    "2.4GHz와 5GHz 중 덜 혼잡한 주파수로 자동으로 연결시켜 주는 기술", 0);
            mDbOpenHelper.insertColumn("머리 추적(head tracking)",
                    "가상현실(VR)을 위해 착용하는 HMD(Head mounted Display)에서 가속도 센서(accelerometer sensor), 자이로 센서(gyroscope sensor), " +
                            "자기장 센서(magnetometer sensor) 등을 사용하여 사용자 머리의 움직임을 추적하는 기술", 0);
            mDbOpenHelper.insertColumn("소프트 로봇(soft robot)",
                    "기존의 딱딱한(hard) 금속을 이용한 로봇이 아닌 유연한(soft) 소재로 만든 로봇들을 의미 미국 하버드 대학교에서 문어를 본떠 제작한 옥토봇(Octobot)" +
                            "과 서울대에서 포유동물인 아르마딜로를 본떠 제작한 스누맥스(SNUMAX)가 있다.", 0);
            mDbOpenHelper.insertColumn("VRD(Virtual Retinal Display)",
                    "눈의 망막에 직접 빔을 주사하는 디스플레이 방식으로, 망막 주사 디스플레이(RSD, Retinal Scan Display) 또는 망막 프로젝터(RP, Retinal Projector)라고도 불린다.", 0);
            mDbOpenHelper.insertColumn("스마트 홈 허브(smart home hub)",
                    "음성 인식을 기반으로 가정 내에서 지능형 가상 비서(IPA, Intelligent Personal Assistant) 역할을 제공하는 허브", 0);
            mDbOpenHelper.insertColumn("저속 촬영 영상(time-lapse video, 타임랩스 영상)",
                    "오랜 시간 동안 변화하는 영상을 일정 시간 간격으로 낱장 촬영한 후 이를 압축하여 재생했을 때 시간이 빠르게 지나가는 것처럼 보이는 영상", 0);
            mDbOpenHelper.insertColumn("객체 추적(object tracking, 비디오 트래킹)",
                    "카메라를 통해 촬영되는 영상에서 사람이나 차량과 같이 움직이는 객체(object)를 추적하는 기술을 가리키는 용어로, 비디오 트래킹(video tracking)이라고도 불린다.", 0);
            mDbOpenHelper.insertColumn("객체 탐지(object detection)",
                    "이미지 또는 동영상에서 사람이나 차량 등의 특정 객체(object)를 인식하고, 위치(bounding box)를 찾기 위한 컴퓨터 비전(computer vision) 기술", 0);
            mDbOpenHelper.insertColumn("테크노포비아(technophobia, 기술 공포증)",
                    "점점 복잡해지고 고도화되는 기기들에 대해 거부감 또는 공포감을 갖는 것을 의미하는 용어. 반대로 기술에 대해 지나치게 낙관적으로 예찬하는 용어로 '테크노필리아(technophilia)'가 있다.", 0);
            mDbOpenHelper.insertColumn("지능형(intelligent)",
                    "인공 지능(AI, Artificial Intelligence)을 기반으로 입력된 정보나, 주변 환경으로부터 습득한 정보를 바탕으로 변화하는 상황에 맞게 행동하는 것을 의미하는 형용사", 0);


            // 2017
            mDbOpenHelper.insertColumn("4D printing(fourth dimension printing) ",
                    "일정 시간이나 환경 조건이 갖추어지면 스스로 형태를 변화시키거나 제조되는 자가조립 기술이 적용된 제품을 3D 프린팅하는 기술", 0);
            mDbOpenHelper.insertColumn("USB-C(Universal Serial Bus Type-C, USB Type-C)",
                    "범용 인터페이스 규격인 USB의 표준 중 하나로 2014년 8월 USB IF(Implementers Forum)에서 발표되었다. 기존의 A형에 " +
                            "비하여 크기가 작고, 24핀으로 위아래의 구분이 없어 어느 방향으로든 연결이 가능", 0);
            mDbOpenHelper.insertColumn("NER(Named-Entity Recognition)",
                    "자연어 처리 및 정보검색 등의 목적으로 활용되는 기법으로, 미리 정의한 인물·장소·시간·출처 등의 개체명을 데이터에서 인식하여 추출한 후 " +
                            "사용자의 의도에 맞게 분류되어 저장되며, 이후 각 개체명을 검색했을 시 해당 문서를 빠르게 찾을 수 있다.", 0);
            mDbOpenHelper.insertColumn("굿풋(goodput)",
                    "응용 계층(application layer)의 데이터 패킷을 대상으로 오버 헤드(overhead), 그리고 재전송된 패킷과 같이 손실된 패킷을 제외한 성공적으로 " +
                            "전송된 패킷의 양을 말한다. 이와 반대로, 전송에 실패하여 손실된 패킷들을 배드풋(badput)이라고 하며," +
                            " 데이터 처리율(throughput)은 굿풋과 배드풋의 합을 의미한다.", 0);
            mDbOpenHelper.insertColumn("다크 데이터(dark data)",
                    "특정 목적을 가지고 데이터를 수집하였으나, 이후 활용되지 않고 저장만 되어있는 대량의 데이터", 0);
            mDbOpenHelper.insertColumn("다크 웹(dark web)",
                    "심층 웹(deep web)보다 접근이 더 어려운 웹사이트로, 일반적인 방법으로 접속자나 서버를 확인할 수 없기 때문에 비트코인 불법 거래, " +
                            "랜섬웨어(ransomware)를 이용한 금전 요구 등의 사이버 범죄에 활용된다. FBI가 온라인 마약 거래 웹사이 '실크로드'를 적발하여 폐쇄하면서 대중에게 알려졌다.", 0);
            mDbOpenHelper.insertColumn("WPAN(Wireless Personal Area Network)",
                    "가까운 거리에서 휴대용 단말기 등을 이용하여 각종 센서 장치 및 전자제품 등을 제어하고 정보를 송·수신하는 소형, 저전력, 저가의 무선 통신망", 0);
            mDbOpenHelper.insertColumn("CUI(Conversational User Interface)",
                    "인공 지능, 음성 인식, 자연어 처리 등의 기술들을 기반으로 사람의 생체, 언어, 몸짓을 인식하여 사용자의 언어로 대화하듯이 입력받는 지능형(intelligent)" +
                            " 사용자 인터페이스", 0);
            mDbOpenHelper.insertColumn("데이터베이스 분할(database partitioning)",
                    "하나의 데이터베이스 테이블을 여러 개로 분산 저장하는 것을 의미. 수평 분할(horizontal partitioning)과, 수직 분할(vertical partitioning)이 있다." +
                            " 수평 분할은 테이블을 행(rows) 단위로 나누어 다른 테이블에 분산시키는 방법으로, 대용량의 데이터를 특정 범위나 분류 등을 기준으로 구분하는 경우 사용한다." +
                            " 수직 분할은 테이블을 열(column) 단위로 나누어 다른 테이블에 분산하는 방법으로, 특정한 열의 조회나 갱신이 반복되거나, 또는 특정한 열의 크기가 클 때 사용한다.", 0);
            mDbOpenHelper.insertColumn("데이터베이스 샤딩(database sharding)",
                    "대용량의 데이터베이스 테이블을 물리적으로 다른 데이터 베이스에 수평 분할(horizontal partitioning) 방식으로 분산 저장하여 조회하는 방법을 " +
                            "의미. 분할된 하나의 테이블을 샤드(shard)라고 부르며, 샤딩은 저장된 데이터를 한 곳에만 저장하거나 여러 샤드에 중복해서 저장할 수도 있다.", 0);
            mDbOpenHelper.insertColumn("디지털 트윈(digital twin)",
                    "미국의 세계적인 제조업체 제너럴 일렉트릭(GE)에서 만든 개념으로 물리적인 사물들을 실제 사물과 동일한 가상 모델을 만들어 " +
                            "디지털 자산으로 삼는 것을 의미", 0);
            mDbOpenHelper.insertColumn("로보어드바이저(robo-advisor)",
                    "인공 지능 시스템이 투자자의 요구에 맞춰 시장 상황을 분석하여 자산을 운용하거나 자문 및 관리하는 온라인 금융 서비스", 0);
            mDbOpenHelper.insertColumn("μLED(MicroLED)",
                    "일반 발광 다이오드보다 빠른 반응속도, 높은 전력 효율과 휘도를 갖으며, 쉽게 깨지지 않는 장점을 지닌 초소형의 발광 다이오드. 길이와 " +
                            "면적 또한 크게 줄어 길이는 1/10, 면적은 1/100 수준으로 10~100 마이크로미터 정도의 크기를 갖는다. 이에 따라 스마트워치나 HMD(Head mounted Display)," +
                            " 스마트 섬유와 같이 무게에 민감한 기기 등에 응용될 수 있다.", 0);
            mDbOpenHelper.insertColumn("MSA(MicroService Architecture)",
                    "대규모 소프트웨어 개발에 있어서 하나의 거대한 소프트웨어를 작은 단위로 분리하여, 수정과 조합이 가능할 뿐만 아니라 단독으로 실행이 가능한 독립적인" +
                            " 모듈로 제공하는 아키텍처를 의미. 이렇게 분리된 작은 단위를 마이크로서비스(microservice)라고 부르며, 각 마이크로서비스는 독립적으로 실행·운영·관리된다." +
                            " 서로 간의 연결은 API(Application Programming Service)를 이용", 0);
            mDbOpenHelper.insertColumn("LSA(License Shared Access)",
                    "주파수 사용에 있어서 1차 사용자가 자신이 사용하지 않는 시간대나 지역을 면허를 받은 신규 사용자에게 계약을 통해 이용할 수 있게 하는 주파수 공동 " +
                            "사용 방식을 의미", 0);
            mDbOpenHelper.insertColumn("MCC(Mobile Cloud Computing)",
                    "클라우드 서비스를 이용하여 소비자와 소비자의 파트너가 모바일 기기를 통해 클라우드 컴퓨팅 인프라를 구성하여 여러 가지 정보와 자원을 공유하는 " +
                            "ICT(Information and Communications Technologies) 기술을 의미", 0);
            mDbOpenHelper.insertColumn("WM-bus(Wireless Meter-bus)",
                    "AMI(Advanced Metering Infrastructure)나 스마트 미터링(smart metering)과 같은 가스, 수도, 전기 등의 원격 검침을 위해 사용되는 무선 프로토콜로" +
                            "유럽 표준인 EN 13757-4에 정의. 데이터 통신에서 오버헤드가 매우 적고, 서브-기가 헤르츠 대역인 868·433·169MHz를 사용하며, 배터리 수명은 최장 20년 까지 지속된다.", 0);
            mDbOpenHelper.insertColumn("MC(Mission Critical)",
                    "업무를 수행하는데 있어 오작동 등의 문제가 생기는 경우 조직뿐만 아니라 사회에까지 치명적인 영향을 미칠 수 있는 요소들을 의미", 0);
            mDbOpenHelper.insertColumn("MCPTT(Mission Critical Push To Talk)",
                    "공공 안전을 위해 특화된 기능을 제공하는 LTE 이동통신망 기반의 PTT서비스. 특정 사용자의 단말 주변의 소리를 임의로 청취하거나, " +
                            "긴급 상황 시 강제적으로 통화를 재발신하는 기능을 포함하고 있다.", 0);
            mDbOpenHelper.insertColumn("버퍼블로트(bufferbloat)",
                    "데이터 전송에서 버퍼로 인해 패킷 전송이 오래 지연되는 현상. ACK와 같은 중요한 소형 패킷이 파일이나 " +
                            "동영상 등의 대용량 전송으로 발생하는 큰 패킷에 밀려 버퍼에 갇히게 되며 발생한다.", 0);
            mDbOpenHelper.insertColumn("브레드크럼즈(breadcrumbs)",
                    "프로그램이나 문서, 웹사이트 등에서 사용자의 탐색 경로 또는 위치를 시각적으로 표시하는 GUI를 의미. ", 0);
            mDbOpenHelper.insertColumn("CPS(Cyber-Physical Systems)",
                    "기존의 임베디드 시스템이 휴대폰이나 가전용품 등의 전자기기의 운용에 집중된 것에 반해 사이버네틱스, 메카트로닉스, 센서 네트워크 등 여러 전문 분야가 유기적으로 정보를 공유하는 " +
                            "다학제적접근과 연구를 통해 현실세계의 다양한 물리, 화학 및 기계공학적 시스템을 네트워크를 통해 제어·운용하기 위해 등장." +
                            " 자동차, 항공, 국방 등 광범위한 분야에 응용될 수 있으며, 최근 이를 응용한 스마트 공장, 스마트 그리드 등의 등장으로 생산성 증가 등의 신부가가치 창출에 기여하고 있다.", 0);
            mDbOpenHelper.insertColumn("산업 인터넷(industrial internet)",
                    "미국의 제너럴 일렉트릭(GE)에서 만든 용어로, 제조·철도·전력 산업에서부터 의료·첨단 산업에까지 산업 현장 전반에서 사용하는 장비들에 " +
                            "접목된 인터넷을 의미", 0);
            mDbOpenHelper.insertColumn("SDE(Software-Defined Everything)",
                    "이를 사용함으로써 데이터 센터, 통신망, 컴퓨터 등을 가상화함으로서 직접 하나하나 명령어를 입력하여 제어하는 것이 아닌, 프로그래밍을 통한 지능적인 제어로 효율적인" +
                            " 운영 및 관리가 가능하다.", 0);
            mDbOpenHelper.insertColumn("SDDC(Software-Defined Data Center)",
                    "각종의 소프트웨어 정의 기술을 활용하여 데이터 센터의 서버·스토리지·네트워크·보안장비 등의 모든 자원을 " +
                            "가상화하고, 이를 인적 자원의 개입 없이 소프트웨어를 이용하여 제어·운용하는 것을 의미.", 0);
            mDbOpenHelper.insertColumn("스레드 프로토콜(Thread protocol)",
                    "2014년 구글사의 주도로 설립된 스레드 그룹에서 개발한 통신 규약으로, IEEE 802.15에서 표준을 제정한 저전력 단거리 " +
                            "무선망(6LoWPAN)을 기반으로 스마트 홈 기기 간에 사물 인터넷(IoT)을 제공하는 무선 프로토콜. 128비트 주소 체계인 IPv6를 기반으로 하며, " +
                            "AES(Advanced Encryption Standard)를 적용하여 보안기능을 강화했으며, 저전력 무선 통신 기술 지그비 (Zigbee)의 전력 기능을 가지는 등의 특징을 " +
                            "갖고 있다. 네스트 랩스는 이것이 사용된 도어락과 온도 조절기 등의 제품을 선보인 바 있다.", 0);
            mDbOpenHelper.insertColumn("스마트 공장(Smart Factory)",
                    "제품의 기획과 설계부터 유통·판매에 이르기까지의 생산의 모든 과정에 정보통신기술(ICT)을 적용하여 생산성과 제품 품질의 향상, 고객의 만족도" +
                            " 증대 등 다양한 이점을 누릴 수 있는 지능형 공장. 제품의 설계 및 개발 단계에서 사이버" +
                            "물리 시스템(CPS, Cyber Physical Systems)을 활용하여 모의실험을 함으로써 자산을 더욱 효율적으로 사용하는 것이 가능", 0);
            mDbOpenHelper.insertColumn("스마트 데이터(smart data)",
                    "단순 수집된 빅데이터에서 추출된 실제적인 가치를 지닌 유용한 데이터. 정확성(accurate), 실행 가능성(actionable), 신속성(agile)의 특징이 있다.", 0);
            mDbOpenHelper.insertColumn("스마트 도시(smart city)",
                    "기존의 U-City(Ubiquitous City)에 사물 인터넷과 인공 지능 기술이 결합된 차세대 개념으로 최신 정보통신기술(ICT)들을 활용하여 도시의 " +
                            "자산들을 지능적 으로 관리·운용하고, 시민들에게 안전하고 윤택한 삶을 제공하는 도시를 의미", 0);
            mDbOpenHelper.insertColumn("시민 해킹(civic hacking)",
                    "사회 공공 문제를 해결하기 위해 ICT 개발자와 시민 등의 다양한 사람들이 자발적으로 모여 ICT 기술을 활용하여 문제를 해결하는 활동", 0);
            mDbOpenHelper.insertColumn("ANSI lumen(American National Standards Institute lumen)",
                    "프로젝터의 밝기 구별을 위해 미국 표준 협회(ANSI, American National Standard Institute)의 표준에서 제시한 휘도의 측정 단위", 0);
            mDbOpenHelper.insertColumn("애그노스틱 기술(agnostic technology)",
                    "해당 시스템의 운용에 대한 지식이 없더라도 기능을 수행하는데 문제가 없게 하는 기술", 0);
            mDbOpenHelper.insertColumn("암호 키 분배(key distribution)",
                    "데이터의 송·수신 시 암호 기술의 적용을 위해 사용자 간에 암호 키를 공유하는 기술.", 0);
            mDbOpenHelper.insertColumn("QKD(Quantum Key Distribution)",
                    "양자 통신을 위해 비밀키를 분배하고 관리하는 기술", 0);
            mDbOpenHelper.insertColumn("양자 역학(quantum mechanics)",
                    "현대 물리학의 한 분야로 양자론의 기초를 이루는 물리학이론의 체계", 0);
            mDbOpenHelper.insertColumn("QIS(Quantum Information Science)",
                    "물리학의 양자 효과에 기초를 둔 정보 과학의 연구 분야로, 양자적인 상태로 주어진 정보를 처리하는 기술과 이를 응용한 여러" +
                            " 과학 기술들을 포괄한다.", 0);
            mDbOpenHelper.insertColumn("양자 통신(quantum communication)",
                    "양자(quantum)의 특성인 중첩성(superposition)을 이용하여 통신하는 방법으로, 양자 역학의 특성을 이용하여 양자 암호의 " +
                            "양자 암호 키 분배(QKD) 기술로 큐비트(qubit)를 전송(quantum teleportation)하는 기술. 양자 통신은 도청이 불가능한 통신망을 제공할 수 있다." +
                            " 양자역학의 특성인 불가복사성(nocloning)과 양자 측정의 비가역성(irreversibility)으로 인해 송신자와 수신자 이외의 제 3자가 도청을 위해 양자 " +
                            "상태를 측정하려는 순간 양자 상태 자체가 변화하게 되어, 수신자가 도청 시도를 파악할 수 있게 한다. 현재 양자 통신 채널은 거리에 제약이 있으며," +
                            " 증폭기가 없는 순수 광섬유로 연결된다.", 0);
            mDbOpenHelper.insertColumn("SLAM(Simultaneous Localization And Mapping)",
                    "로봇이 공간을 돌아다니면서 부착된 카메라와 센서를 이용하여 지도를 작성하는 것", 0);
            mDbOpenHelper.insertColumn("HMM(Hidden Markov Model)",
                    "시스템이 숨겨져 있는 상태(state) 정보와 관찰 가능한 출력(output) 정보 두 가지 요소로 이루어져 있는 모델. " +
                            "이는 출력 정보만을 가지고 숨겨져 있는 상태 정보를 추정해야 하는데, 이와 같은 특성은 음성 인식이나 자연어 처리와 같은 " +
                            "대량의 출력 데이터의 패턴을 분석하여 입력 정보를 추론해야 할 때에 응용된다.", 0);
            mDbOpenHelper.insertColumn("이중 정보기술(Bimodal IT)",
                    "세계적인 IT 리서치 그룹인 가트너에서 2014년 기업의 IT 조직 모델로 제시한 용어로, 서로 다른 두 개의 정보기술 전략을 병행하여 운영하는 것을" +
                            " 의미한다기존의 IT 전략으로 내부 시스템의 안정성을 유지함과 동시에, 혁신적인 IT 전략으로 급변하는 시장의 수요에 맞춰 발 빠르게 대응하는 것을 예로 들 수 있다.", 0);
            mDbOpenHelper.insertColumn("자동 통역(automatic speech translation)",
                    "서로 다른 언어를 사용하는 사람들이 각자의 모국어를 사용해 대화할 수 있도록 하는 기술", 0);
            mDbOpenHelper.insertColumn("6LoWPAN(IPv6 over Low Power Wireless Personal Area Network)",
                    "저전력, 적은 대역폭을 사용하는 WPAN 상에 IPv6 주소를 적용하여 기존의 IP 네트워크와 연결하는 네트워크 기술. 현재 지그비(Zigbee), " +
                            "스레드(Thread), mbed OS 등 대부분의 사물인터넷 관련 아키텍처나 오픈소스 플랫폼에서 수용하고 있다.", 0);
            mDbOpenHelper.insertColumn("EFF(Electronic Frontier Foundation)",
                    "1990년 활동가 미쉘 케이포(Mitch Kapor)와 존 페리 바를로(John Perry Barlow)가 인터넷에서의 표현의 자유, 저작물의 자유로운 사용, " +
                            "프라이버시 보호 등의 권리를 위해 설립한 국제 비영리 단체", 0);
            mDbOpenHelper.insertColumn("ESL(Electronic Shelf Label)",
                    "상품 정보를 표시하는 태그 기기와 유·무선으로 통신할 수 있는 게이트웨이로 구성되어, 진열대에 통신망을 연결하여 각 상품의" +
                            " 가격·원산지 등의 정보를 실시간으로 표시해주는 전자 종이(e-paper) 단말을 의미", 0);
            mDbOpenHelper.insertColumn("제4차 산업혁명(the fourth industrial revolution)",
                    "사이버 물리 시스템(CPS), 사물 인터넷(IoT), 인공지능(AI), 빅데이터(big data) 등 첨단 정보통신기술(ICT)을 활용하여 모든 사물들을 " +
                            "자동적, 지능적으로 제어할 수 있는 시스템과 초연결(hyper-connection)을 지향하는 산업 혁명을 의미", 0);
            mDbOpenHelper.insertColumn("GCSE(Group Communication System Enablers)",
                    "2015년 3GPP 릴리즈 12에서 처음 표준화된 기술로, LTE 기반의 멀티미디어 방송 다중 송출 서비스(eMBMS) 등을 이용하여 다수의 사람들이" +
                            " 밀집된 대형 재난 등의 상황에서 사람들에게 그룹 통신을 제공하는 기술. 하나의 채널을 통해 대규모 그룹 통신이 가능한 것이 특징이며," +
                            " 이로 인해 수백 명이 동시에 통화를 하거나 데이터를 주고받을 수 있다.", 0);
            mDbOpenHelper.insertColumn("초저지연(ultra-low latency)",
                    "사물 통신(IoT)에서 종단 간(end-to-end)의 지연 시간(latency)이 매우 낮은 것(ultra-low)을 의미", 0);
            mDbOpenHelper.insertColumn("출구 필터링(egress filtering)",
                    "조직 내부의 위협이 밖으로 전파되어 기업 외부의 IT 자원에 영향을 미치지 않도록 내부 통신망의 라우터나 방화벽에서 외부로 나가는 데이터의" +
                            " 속성을 검사하고 흐름을 제어하는 보안 기법", 0);
            mDbOpenHelper.insertColumn("해저 통신 케이블(submarine communications cable)",
                    "육지의 도시나 국가 간의 데이터 통신을 위해 해저에 설치하는 케이블", 0);
            mDbOpenHelper.insertColumn("SNOC(Submarine Network Operation Center)",
                    "2016년 6월 부산 송정에 설치되었으며, 아시아와 태평양 지역의 해저 통신망을 통합 관제하는 역할", 0);
            mDbOpenHelper.insertColumn("APG(Asia Pacific Gateway)",
                    "아시아 지역의 트래픽 문제를 해결하기 위해 한국·일본·중국·홍콩·대만·베트남 등 9 개국 11개 지역을 연결하는 해저 광케이블", 0);
            mDbOpenHelper.insertColumn("NCP cable system(New Cross Pacific cable system)",
                    "아시아 태평양의 한국·일본·중국·대만과 북미 지역을 연결하는 총길이 14,000km에, 전송량은 초당 80Tbps에 이르는 해저 광케이블", 0);
            mDbOpenHelper.insertColumn("지능정보기술",
                    "인공 지능(AI)과 IBCM(IoT, Big Data, Cloud Computing, Mobile)을 결합하여 인간의 인지·학습·추론 등의 고차원적인 정보처리 능력을 구현 " +
                            "하는 것", 0);
            mDbOpenHelper.insertColumn("DX(Digital Transformation)",
                    "AI, IoT, Big Data 등의 최신 디지털 기술들을 활용하여 현대의 끊임없이 변화하는 환경에 맞게 기존의 전통적인 사회를 혁신하여 경쟁력을 확보하는 것", 0);
            mDbOpenHelper.insertColumn("GDPR(General Data Protection Regulation)",
                    "유럽 의회에서 유럽 연합 내의 모든 개인 정보에 대한 보안을 강화하고 통일하기 위해 만든 규정(Regulation(EU) 2016/679)이다.", 0);
            mDbOpenHelper.insertColumn("데이터 이동 권리(the right to data portability)",
                    "2018년 5월 25일부터 적용되는 EU의 개인정보보호 규정(GDPR; General Data Protection Regulation)에 속한 주요 항목 중 하나로, 사용자가 " +
                            "이용 중인 서비스에서 다른 서비스로 자신의 정보를 자유롭게 이동할 수 있는 권리", 0);
            mDbOpenHelper.insertColumn("MDFPP(Mobile Device Fundamentals Protection Profile)",
                    "모바일 기기 분야의 국제 공통평가기준(CC; Common Criteria)을 준용한 보안 인증으로 화면 잠금, 와이파이(Wi-Fi) 보안, 키 관리, 암호 모듈," +
                            " 기기 암호화 등 80 여 개의 보안 요구 사항을 기술하고 있다.", 0);
            mDbOpenHelper.insertColumn("트러스트존 기술(TrustZone technology)",
                    "칩 설계회사인 ARM(Advanced RISC Machine)에서 개발한 기술로, 하나의 프로세서 내에 일반 애플리케이션을 처리하는 " +
                            "일반 구역(normal world)과 보안이 필요한 애플리케이션을 처리하는 보안 구역(secure world)으로 분할하여 관리 하는 하드웨어 기반의 보안 기술", 0);
            mDbOpenHelper.insertColumn("독싱(doxing)",
                    "'dropping docs', 즉 '문서를 떨어뜨리다'에서 파생된 용어로, 특정 개인이나 조직을 해킹하여 빼낸 정보를 온라인에 공개하는 행위", 0);
            mDbOpenHelper.insertColumn("독스웨어(doxware)",
                    "해킹을 통해 획득한 정보를 온라인에 공개하는 '독싱 (doxing)'과 시스템에 대한 접근을 막거나 데이터를 암호화하여 해제를 위해 금품을 요구하는" +
                            " '랜섬웨어(ransomware)'의 기능이 결합된 악성코드", 0);
            mDbOpenHelper.insertColumn("NR(New Radio)",
                    "무선 통신 관련 국제 표준 기구인 3GPP에서 2016년 12월 개최된 기술총회(TSG)에서 처음 제시된 용어로, 5세대(5G) 이동통신의 실현을 위한 무선" +
                            " 접속 기술을 말한다.", 0);
            mDbOpenHelper.insertColumn("촉각 인터넷(Tactile Internet)",
                    "사람의 감각 중 가장 빠르기 인지할 수 있는 촉각 민감도 수준의 지연시간이 요구되는 인터넷 서비스", 0);
            mDbOpenHelper.insertColumn("PDR(Peak Data Rate)",
                    "스마트폰과 같은 이동 통신 기기가 데이터를 전송할 수 있는 최고 속도", 0);
            mDbOpenHelper.insertColumn("사용자 체감 전송 속도(User Experienced Data Rate)",
                    "국제 전기 통신 연합의 전파 통신국(ITU-R)이 5G 통신망(IMT-2020)의 성능 요구 사항 중 하나로 통신망의 이용자가 " +
                            "시간과 장소에 상관없이 서비스를 이용할 때 체감하는 최소한의 데이터 전송 속도를 말한다.", 0);
            mDbOpenHelper.insertColumn("OTA(Over-The-Air programming)",
                    "무선 통신망에서 시스템 등록에 관한 정보를 무선 채널을 통해 송.수신하여, 해당 기기의 설정이나 소프트웨어 등을 갱신하는 방법으로, 실시간으로 각 기기의 설정, " +
                            "펌웨어 등을 갱신하여 최신 상태를 유지하게 한다.", 0);
            mDbOpenHelper.insertColumn("Immersive VR(Immersive Virtual Reality)",
                    "가상으로 만들어진 3차원 공간에서 HMD(Head Mounted Display)나 데이터 글로브(Data Globe)와 같은 장치를 이용하여 사용자의 몰입감을 증대시켜" +
                            " 가상세계의 일부가 된 것과 같이 몰입하게 해주는 것을 의미하며, 몰입형 가상 현실이라고도 불린다.", 0);
            mDbOpenHelper.insertColumn("ATSC 3.0(Advanced Television Systems Committee standard 3.0 version)",
                    "미국 디지털 TV 방송 표준화 단체인 ATSC에서 제정한 기술 표준으로 모바일 TV, 3D TV, 4K UHD, HDR(High Dynamic Range), HFR(High Frame Rate) 등을 지원하며," +
                            " 국내에는 2016년 지상파 초고화질(UHD) 방송 표준 규격으로 채택되어 2017년 2월부터 이를 활용한 방송이 시작되었다.", 0);
            mDbOpenHelper.insertColumn("ROUTE(Real-time Object delivery over Unidirectional Transport)",
                    "인터넷이나 방송망에서 인터넷 프로토콜을 기반으로 실시간으로 초고화질(UHD) 방송을 전송하기 위해 사용되는 규격으로, 미국 디지털 TV 방송" +
                            " 표준화 단체인 ATSC에서 제정한 기술 표준인 ATSC 3.0에 핵심 전송 프로토콜 기술로 채택되었다.", 0);
            mDbOpenHelper.insertColumn("ESG(Electronic Service Guide)",
                    "기존의 디지털 TV에서 텍스트로 간단한 방송 프로그램의 정보만을 제공하던 EPG(Electronic Program Guide)와는 달리, 동영상이나 애플리케이 " +
                            "션, 이미지 등을 활용하여 방송 프로그램 정보를 안내하는 부가 서비스이다.", 0);
            mDbOpenHelper.insertColumn("로봇 3원칙(Three Laws of Robotics)",
                    "미국의 작가 아이작 아시모프(Isaac Asimov)가 1942년에 발표한 단편 'Runaroud' 에서 처음 언급한 것으로 로봇 자신의 존재를 보호하기 " +
                            "위한 로봇 안전 준칙을 의미한다.", 0);
            mDbOpenHelper.insertColumn("소셜 로봇(Social Robot)",
                    "사람의 말이나 몸짓 등의 행동에 따라 사람과 교감하고 의사소통을 하는 자율 로봇 JIBO사의 가족용 " +
                            "로봇인 '지보(JIBO)', 일본 소프트뱅크의 인간형 로봇 '페퍼(Pepper)' 등이 있다.", 0);
            mDbOpenHelper.insertColumn("A/B 테스팅(A/B testing)",
                    "웹사이트나 모바일 애플리케이션 등의 디지털 마케팅에서 두 가지 이상의 시안을 만들어 사용자에게 무작위로 노출하여 어느 쪽의 선호도가 더" +
                            " 높은 지 판별하는 실험 방법. 직관적이고 간단하지만, 단순한 선호도 조사에 그칠 뿐 사용자가 어떤 이유로 선택했는지 알 수 없다는 단점이 있다.", 0);
            mDbOpenHelper.insertColumn("스마트폰 좀비(smartphone zombie)",
                    "스마트폰을 보느라 고개를 숙이고 길을 걷는 사람을 좀비에 빗댄 말. '스몸비(smombie)'라고도 불린다.", 0);
            mDbOpenHelper.insertColumn("간편 결제 서비스(Simple Payment Service)",
                    "온·오프라인 상거래에서 복잡한 절차를 생략하고 간단하게 결제하는 전자 결제 서비스", 0);
            mDbOpenHelper.insertColumn("대화형 상거래(Conversational Commerce)",
                    "메신저를 이용해 상품을 구매하거나, 쇼핑 정보를 주고받으며, 고객 맞춤 서비스를 제공하는 전자상거래", 0);
            mDbOpenHelper.insertColumn("CDO(Chief Data Officer)",
                    "기업에서 정확하고 체계적으로 데이터를 관리하고, 데이터로부터 양질의 정보를 발굴하며, 전사적으로 데이터 전략·표준·절차·책임 " +
                            "정책의 수립과 시행을 담당하는 최고 임원을 의미", 0);
            mDbOpenHelper.insertColumn("데이터 통합 관리(Data Governance)",
                    "양질의 데이터를 발굴하고 이를 효율적으로 관리,통제하여 비즈니스 자산으로 활용하기 위한 데이터 통합 관리 체계", 0);


            // 보안 관리
            mDbOpenHelper.insertColumn("PET(Privacy Engancing Technology)",
                    "개인정보 위험관리 기술이다. 최근 심각한 위험으로 대두되고 있는 개인정보 침해위험을 관리하기 위한 핵심 기술로 암호화," +
                            " 익명화 등 개인정보를 보호하는 기술에서 사용자가 직접 개인정보를 통제하기 위한 기술까지 다양한 사용자 프라이버시 보호기술을 통칭한다.", 0);
            mDbOpenHelper.insertColumn("Digital Forensics",
                    "범죄의 증거로 사용될 수 있는 컴퓨터, 휴대전화, 인터넷 등의 디지털 저장매체에 존재하는 디지털 정보를 수집하는 디지털 수사 과정을 말한다.", 0);
            mDbOpenHelper.insertColumn("DRM(Digital Rights Management)",
                    "데이터의 안전한 배포를 활성화하거나 불법 배포를 방지하여 인터넷이나 기타 디지털 매체를 통해 유통되는 데이터의 저작권을 보호하기" +
                            " 위한 시스템", 0);
            mDbOpenHelper.insertColumn("ONS(Object Naming Service)",
                    "정보가 있는 올바른 컴퓨터를 가리키는 자동화 네트워킹. RFID 코드와 관련된 사물의 구체적인 정보가 있는 서버의 위치를 알려 주는 서비스", 0);
            mDbOpenHelper.insertColumn("IDS(Intrusion Detection System)",
                    "컴퓨터 시스템의 비정상적인 사용, 오용, 남용 등을 실시간으로 탐지하는 시스템", 0);
            mDbOpenHelper.insertColumn("RFID(Radio Frequency IDentification)",
                    "반도체 칩이 내장된 태그, 라벨, 카드 등의 저장된 데이터를 무선주파수를 이용하여 비접촉으로 읽어내는 인식시스템", 0);
            mDbOpenHelper.insertColumn("저작권(Copyright)",
                    "원저작물의 창작자가 저작물의 사용과 배포에 있어 일반적으로 제한된 시간 동안 배타적 권리를 인정하는 법적인 권리이다. 배타적 권리는 저작권법에서" +
                            " 정한 제한 및 예외 사항에 의해 제한되므로 절대적인 권리는 아니다.", 0);
            mDbOpenHelper.insertColumn("DLP(Data Leakage/Loss Prevention)",
                    "내부정보 유출방지 솔루션. 사내 직원이 사용하는 PC와 네트워크 상의 모든 정보를 검색하고 사용자의 행위를 탐지, 통제해 외부로의 유출을 사전에 막는다.", 0);
            mDbOpenHelper.insertColumn("CC(Common Criteria)",
                    "정보 보호 제품의 평가 기준을 규정한 국제 표준(ISO 15408)", 0);
            mDbOpenHelper.insertColumn("SSL(Secure Sockets Layer)",
                    "월드 와이드 웹 브라우저와 웹 서버 간에 데이터를 안전하게 주고받기 위한 업계 표준 프로토콜. 미국 넷스케이프 커뮤니케이션스사가 개발했고," +
                            " 마이크로소프트사 등 주요 웹 제품 업체가 채택하고 있다.", 0);
            mDbOpenHelper.insertColumn("프록시 서버(Proxy Server)",
                    "PC 사용자와 인터넷 사이에서 중개자 역할을 하는 서버로 크게 방화벽 기능과 캐시 기능을 수행한다." +
                            "\n방화벽(Firewall) 기능은 컴퓨터 시스템에 방화벽을 설치하는 경우 외부자와 연결하여 통신이 가능하도록 하며 HTTP, FTP, Gopher 프로토콜을 지원한다." +
                            "\n캐시 기능은 많은 요청이 발생하는 데이터를 프록시 서버에 저장해 두었다가 요청이 있을 경우 신속하게 전송한다.", 0);
            mDbOpenHelper.insertColumn("HDCP(High-bandwidth Digital Content Protection)",
                    "PC와 같은 영상 재생 기기로부터 디스플레이 등의 표시장치에 디지털 신호를 송수신하는 경로에서 콘텐츠를 암호화하여 전송함으로써 정보가 부정하게" +
                            " 복사되는 것을 방지하는 저작권 보호 기술. 아날로그 영상은 복사를 하여도 영상이 열화되어 큰 문제가 없으나 디지털 영상 신호는 완전한 복제가 " +
                            "가능하여 이를 보호할 필요가 있다.", 0);
            mDbOpenHelper.insertColumn("빅 데이터(Big Data)",
                    "정형·반정형·비정형 데이터세트의 집적물, 그리고 이로부터 경제적 가치를 추출 및 분석할 수 있는 기술", 0);
            mDbOpenHelper.insertColumn("CCL(Creative Commons License)",
                    "자신의 창작물에 대하여 일정한 조건을 명시하여 타인이 자유롭게 이용할 수 있는 라이선스", 0);
            mDbOpenHelper.insertColumn("PIA(Privacy Impact Assessment)",
                    "개인 정보를 활용하는 새로운 정보시스템의 도입 및 기존 정보시스템의 중요한 변경 시 시스템의 구축, 운영이 기업의 고객은 물론 국민의 사생활에 " +
                            "미칠 영향에 대해 미리 조사, 분석, 평가하는 제도", 0);
            mDbOpenHelper.insertColumn("RPO(Recovery Point Objective)",
                    "조직에서 발생한 여러가지 재난 상황으로, IT 시스템이 마비되었을 때 각 업무에 필요한 데이터를 여러 백업 수단을 활용하여 복구할 수 있는 기준점", 0);
            mDbOpenHelper.insertColumn("ICMP(Internet Control Message Protocol)",
                    "TCP/IP 기반의 인터넷 통신 서비스에서 인터넷 프로토콜(IP)과 조합하여 통신 중에 발생하는 오류의 처리와 전송 경로의 변경 등을 위한 제어 메시지를" +
                            " 취급하는 무연결 전송용 프로토콜(RFC. 792). OSI 기본 참조 모델의 망 계층에 해당한다.", 0);
            mDbOpenHelper.insertColumn("WEP(Wired Equivalent Privacy)",
                    "유선 랜(LAN)에서 기대할 수 있는 것과 같은 보안과 프라이버시 수준의 무선 랜(WLAN)의 보안 프로토콜", 0);
            mDbOpenHelper.insertColumn("WPA(Wi-Fi Protected Access)",
                    "무선 랜 보안 표준의 하나. WEP(Wired Equivalent Privacy) 키 암호화를 보완하는 TKIP(Temporal Key Integrity Protocol)라는 IEEE 802.11i 표준을" +
                            " 기반으로 하고 있으며, 인증 부문에서도 802.1x 및 EAP(Extensible Authentiction Protocol)를 도입해 성능을 높인 것이다. 특히 패킷당 키 할당 기능" +
                            ", 키값 재설정 등 다양한 기능이 있기 때문에 해킹이 불가능하고 네트워크에 접근 시 인증 절차를 요구한다.", 0);
            mDbOpenHelper.insertColumn("IPSec(IP security protocol)",
                    "안전에 취약한 인터넷에서 안전한 통신을 실현하는 통신 규약. 인터넷상에 전용 회선과 같이 이용 가능한 가상적인 전용 회선을 구축하여 데이터를 " +
                            "도청당하는 등의 행위를 방지하기 위한 통신 규약이다.", 0);
            mDbOpenHelper.insertColumn("OTP(One-Time Password)",
                    "로그인할 때마다 그 세션에서만 사용할 수 있는 1회성 패스워드를 생성하는 보안 시스템", 0);
            mDbOpenHelper.insertColumn("공인인증서(Certificate)",
                    "전자 서명법에 의한 공인 인증 기관이 발행한 인증서이다. 인터넷 뱅킹, 전자 민원, 전자 입찰, 인터넷 주택 ㅈ청약 등에서 신원 확인 수단으로 " +
                            "사용하고 있으며, 또한 각종 홈페이지의 로그인 수단으로도 활용되고 있다.", 0);
            mDbOpenHelper.insertColumn("생체 인식(Biometrics)",
                    "개인마다 다른 지문, 홍채, 땀샘 구조, 혈관 등 개인의 독특한 생체 정보를 추출하여 정보화시키는 인증 방식이다.", 0);
            mDbOpenHelper.insertColumn("i-PIN(internet Personal Identification Number)",
                    "대면 확인이 불가능한 인터넷상에서 주민등록번호를 대신해 본인임을 확인받을 수 있는 사이버 신원 인증 방법론이다.", 0);
            mDbOpenHelper.insertColumn("BCP(Business Continuity Planning)",
                    "재난 발생 시 비즈니스의 연속성을 유지하기 위한 계획", 0);
            mDbOpenHelper.insertColumn("CRL(Certificate Revocation List)",
                    "더 이상 유효하지 않은 인증서 목록", 0);
            mDbOpenHelper.insertColumn("EPC Class(Electronic Product Code Class)",
                    "RFID 태그 종류로 class 1은 태그 제조업체에서 고유한 인식번호가 제작단계에서 부여되어 물체에 부착된 뒤에 리더로 " +
                            "읽기만을 제공하는 읽기 전용 수동형 태그이고, class 2는 읽기/쓰기가 가능하고 암호화를 적용할 수 있는 더 발전된 형태의 수동형 태그며, class 3은" +
                            " 배터리를 내장하여 리더로부터 오는 전력을 태그정보 전송에만 활용하도록 하여 인식거리를 증가시킨 반 능동형 태그이며, class 4는 태그끼리 통신이 " +
                            "가능하며, 애드 혹 네트워크 구성이 진화된 형태의 태그임.", 0);
            mDbOpenHelper.insertColumn("인증기관(Certification Authority)",
                    "보안 적격 여부 및 메시지 암호화를 위한 공개 키의 발급과 관리를 담당하는 신뢰성이 보장된 온라인상의 기관", 0);
            mDbOpenHelper.insertColumn("SAM(Secure Application Module)",
                    "스마트 카드 보안 응용 모듈. 카드 판독기 내부에 장착되어 카드와 단말기의 유효성을 인증하고 통신 데이터를 암호화하여 정보의 노출 방지 및 " +
                            "통신 메시지의 인증 및 검증한다.", 0);
            mDbOpenHelper.insertColumn("CLMS(Copyright License Management System)",
                    "디지털저작권거래소에서 운영하고 있는 권리자와 이용자 간에 보다 편리하게 저작물을 이용할 수 있도록 저작권 이용허락계약을 온라인으로 지원하는 시스템", 0);
            mDbOpenHelper.insertColumn("정부 개인식별번호(Goverment-Personal Identification Number)",
                    "정부가 추진하고 있는 주민 등록 번호 대체 수단. 주민 번호 오남용, 도용 등 개인 정보 보호를 강화하기 위해 공공 기관 웹 사이트 회원 가입이나" +
                            " 게시판 이용 시 정부 개인 식별 번호(G-PIN)를 전 공공 기관에서 사용해야 한다. 민간 부분에서 사용 중인 인터넷 개인 식별 번호(i-PIN)와 G-PIN을" +
                            " 연계하여 개인이 하나의 PIN을 이용하여 사용할 수 있다.", 0);
            mDbOpenHelper.insertColumn("키 페어(Key Pair)",
                    "공개 키 암호 알고리듬에 사용되는 개인 키와 공개 키 쌍", 0);
            mDbOpenHelper.insertColumn("방화벽(Firewall)",
                    "기업이나 조직의 모든 정보가 컴퓨터에 저장되면서, 외부에서 내부, 내부에서 외부의 정보통신망에 불법으로 접근하는 것을 차단하는 시스템", 0);
            mDbOpenHelper.insertColumn("소프트웨어 에스크로(임치)(Software Escrow)",
                    "소프트웨어 개발자의 지식재산권을 보호하고 사용자에게는 저렴한 비용으로 소프트웨어R를 안정적으로 사용하고 유지 보수를 받을 수 있도록 " +
                            "하기 위해서 소스 프로그램과 기술 정보 등을 제3의 기관에 보관하는 것", 0);
            mDbOpenHelper.insertColumn("마이핀(MT-PIN)",
                    "개인 정보 보호를 위해 2014년 8월 7일 개인정보보호법의 시행에 따라 도입된 주민 등록 번호를 대신할 수 있는 무작위 13자리 번호", 0);
            mDbOpenHelper.insertColumn("CAPTCHA(Completely Automated Public Turing test to tell Computers and Humans Apart)",
                    "주로 웹사이트 회원 가입 절차에서 사용자가 사람인지 컴퓨터인지를 판별하기 위한 시도 응답 인증 방식(challenge-response authentication mechanism)." +
                            " 컴퓨터가 구별할 수 없는 찌그러진 문자나 단번에 인식하기 어려운 숫자 등을 문제로 내 사람만 캡차 시스템을 통과할 수 있게 만든다.", 0);
            mDbOpenHelper.insertColumn("CTI(Cyber Threat Intelligence)",
                    "조직의 정보 자산에 위협이 될 수 있는 취약 요소, 과거 공격 등 관련 정보를 기반으로 사이버 보안 위협에 효과적으로 대응하는 방법", 0);
            mDbOpenHelper.insertColumn("전체 백업",
                    "데이터 전체를 대상으로 백업을 수행하는 방식", 0);
            mDbOpenHelper.insertColumn("중분 백업(Incremental Backup)",
                    "백업 대상 데이터 중 변경되거나 증가된 데이터만을 대상으로 백업을 수행하는 방식" +
                            "\n차별 증분(Differential Incremental) : 전체 백업 이후 변화가 있는 데이터만을 대상으로 백업을 수행되는 방식" +
                            "\n누적 증분(Cumulative Incremental) : 차별 증분과 유사하나 전체 백업 이후 변경분을 누적하여 백업을 수행하는 방식", 0);


            // 보안 위협의 구체적인 형태
            mDbOpenHelper.insertColumn("사이버 협박(Cyber Bullying)",
                    "개인이나 집단이 인터넷에서 상대에게 나타내는 적대 행위를 말한다. 전자 우편, 문자 메시지, 인터넷 메신저 따위로 모욕이나 위협을 주는 " +
                            "메시지를 보내거나, 소문을 퍼뜨리고, 모욕적인 사진을 공개하여 누군가를 괴롭히는 행위이다.", 0);
            mDbOpenHelper.insertColumn("DDoS(Distributed Denial of Service)",
                    "해킹 방식의 하나로서 여러 대의 공격자를 분산 배치하여 동시에 '서비스 거부 공격(Denial of Service attack；DoS)'을 함으로써 시스템이 더" +
                            " 이상 정상적 서비스를 제공할 수 없도록 만드는 것을 말함", 0);
            mDbOpenHelper.insertColumn("VoIP 보안 위협(VoIP Security Threat)",
                    "음성패킷을 불법으로 수집·조합해 통화 내용을 재생하는 도청(sniffing) 위협", 0);
            mDbOpenHelper.insertColumn("랜섬웨어(Ransomware)",
                    "미국에서 발견된 스파이웨어 형태의 신종 악성 프로그램이다. 인터넷 사용자의 컴퓨터에 잠입해 내부 문서나 파일 등을 임의로 암호하해 " +
                            "사용자가 열지 못하도록 만든 후 암호 해독용 프로그램의 전달을 조건으로 사용자에게 돈을 요구하기도 한다.", 0);
            mDbOpenHelper.insertColumn("디지털 발자국(Digital Footprint)",
                    "사람들이 여러 웹페이지에 로그인을 하거나 결제 정보를 입력하는 등 온라인 활동을 하면서 남긴 기록", 0);
            mDbOpenHelper.insertColumn("워터링 홀(Watering Hole)",
                    "표적으로 삼은 특정 집단이 주로 방문하는 웹 사이트를 감염시키고 피해 대상이 그 웹사이트를 방문할 때까지 기다리는 웹 기반 공격", 0);
            mDbOpenHelper.insertColumn("백도어(Back Door, Trap Door)",
                    "프로그램 개발이나 유지 보수, 유사 시 문제 해결 같은 것을 위해 시스템 관리자나 개발자가 정상적인 절차를 우회하여 시스템에 출입할 수" +
                            " 있도록 임시로 만들어둔 비밀 출입문. 트랩도어(trapdoor)라고도 한다. 원래의 의미는 그렇지만 대부분의 경우 비밀문은 보안상 허점을 역이용하여" +
                            " 크래커들이 시스템에 침입한 뒤 자신이 원할 때 다시 침입하거나, 권한 획득을 쉽게하기 위하여 만들어 놓은 일종의 비밀 통로를 의미한다.", 0);
            mDbOpenHelper.insertColumn("제로 데이 공격(Zero Day Attack)",
                    "보안 취약점이 발견되었을 때 그 문제의 존재 자체가 널리 공표되기도 전에 해당 취약점을 악용하여 이루어지는 보안 공격", 0);
            mDbOpenHelper.insertColumn("스머핑(SMURFING)",
                    "인터넷 프로토콜(IP) 브로드캐스트나 기타 인터넷 운용 측면을 이용하여 인터넷망을 공격하는 행위. IP와 인터넷 제어 메시지 프로토콜(ICMP)의 특성을 이용한다고 알려져 있다.", 0);
            mDbOpenHelper.insertColumn("타이포스쿼팅(Typosquatting)",
                    "사이트에 접속할 때 주소를 잘못 입력하거나 철자를 빠뜨리는 실수를 이용하기 위해 이와 유사한 유명 도메인을 미리 등록하는 일. URL" +
                            " 하이재킹(hijacking)이라고도 한다.", 0);
            mDbOpenHelper.insertColumn("핵티비즘(Hacktivism)",
                    "해커(Hacker)와 행동주의(Activism)의 합성어로 자신들의 정치적 목적을 달성하기 위한 수단으로 자신과 노선을 달리하는 정부나 기업, 단체 등의 " +
                            "인터넷 웹 사이트를 해킹하는 일체의 활동", 0);
            mDbOpenHelper.insertColumn("티비싱(Tvishing)",
                    "텔레비전(TV)과 개인정보 낚시질을 뜻하는 피싱(Phishing)의 합성어. 스마트TV에 악성 소프트웨어를 설치해 스마트TV에 대한 최고 접근권한을 획득할" +
                            " 수 있다.", 0);
            mDbOpenHelper.insertColumn("APT(Advanced Persistent Threats)",
                    "다양한 IT 기술과 방식들을 이용해 조직적으로 특정 기업이나 조직 네트워크에 침투해 활동 거점을 마련한 뒤 때를 기다리면서 보안을 무력화시키고 " +
                            "정보를 수집한 다음 외부로 빼돌리는 형태의 공격. \n 공격 방법에는 내부자에게 악성코드가 포함된 이메일을 오랜 기간 동안 꾸준히 발송해 " +
                            "한 번이라도 클릭되길 기다리는 형태, 스턱스넷과 같이 악성코드가 담긴 USB 등으로 전파하는 형태, 악성코드에 감염된 P2P " +
                            "사이트에 접속하면 악성코드에 감염되는 형태 등이 있다.", 0);
            mDbOpenHelper.insertColumn("파밍(Pharming)",
                    "'피싱(Phishing)'에 이어 등장한 새로운 인터넷 사기 수법이다. 넓은 의미에서는 피싱의 한 유형으로서 피싱보다 한 단계 진화한 형태라고 할 수" +
                            " 있다. 이는 해당 사이트가 공식적으로 운영하고 있던 도메인 자체를 중간에서 탈취하는 수법이다.", 0);
            mDbOpenHelper.insertColumn("사회 공학(Social Engineering)",
                    "컴퓨터 보안에 있어서, 인간 상호 작용의 깊은 신뢰를 바탕으로 사람들을 속여 정상 보안 절차를 깨트리기 위한 비기술적 시스템 침입 수단", 0);
            mDbOpenHelper.insertColumn("Zeus",
                    "사용자의 온라인 뱅킹 계정 정보를 탈취하기 위해 개발된 상용 멀웨어이다. 2007년 러시아에서 개발된 것으로 추정되며 주로 이메일 등을 통해" +
                            " 전파되면, 감염된 컴퓨터는 사용자의 모든 키보드 입력 정보를 지정된 곳으로 보낸다.", 0);
            mDbOpenHelper.insertColumn("봇넷(Botnet)",
                    "악성 프로그램에 감염되어 향후에 악의적인 의도로 사용될 수 있는 다수의 컴퓨터들이 네트워크로 연결된 형태를 말한다.", 0);
            mDbOpenHelper.insertColumn("Zombie",
                    "다른 프로그램이나 다른 사용자를 조종하도록 악성코드에 감염된 컴퓨터를 말한다.", 0);
            mDbOpenHelper.insertColumn("특허 괴물(Patent Troll)",
                    "특허권을 비롯한 지적 재산권을 통해 로열티 수입만으로 이익을 창출하는 특허 관리 전문 기업", 0);
            mDbOpenHelper.insertColumn("Malware(Malicious Software)",
                    "악의적인 목적을 위해 작성된 코드 또는 프로그램", 0);
            mDbOpenHelper.insertColumn("스턱스넷(Stuxnet)",
                    "독일 지멘스사의 원격 감시 제어 시스템(SCADA)의 제어 소프트웨어에 침투하여 시스템을 마비하게 할 목표로 제작된 악성코드. 원자력" +
                            " 발전소와 송,배전망, 화학 공장, 송유,가스관과 같은 산업기반 시설에 사용되는 제어 시스템에 침투하여 오작동을 유도하는 명령 코드를 " +
                            "입력해서 시스템을 마비시킨다.", 0);
            mDbOpenHelper.insertColumn("공격용 툴킷(Attack Toolkit)",
                    "네트워크에 연결된 컴퓨터를 공격하려고 사용하는 악성 코드 프로그램을 모아 놓은 것. 대표적인 공격용 툴킷으로는 제우스(Zeus)와 " +
                            "스파이아이(Spyeye) 따위가 있다.", 0);
            mDbOpenHelper.insertColumn("죽음의 핑(Ping of Death)",
                    "인터넷 프로토콜 허용 범위(6만 5536바이트) 이상의 큰 패킷을 고의로 전송하여 발생한 서비스 거부(DoS) 공격. 공격자의 식별 위장이 " +
                            "용이하고 인터넷 주소 하나만으로도 공격이 가능하다.", 0);
            mDbOpenHelper.insertColumn("스플로거(Splogger)",
                    "스팸(Spam)과 블로거(Blogger)의 합성어로 다른 사람의 콘텐츠를 무단으로 복사해 자신의 블로그에 게재하는 블로거 또는 제품 광고나 음란물" +
                            " 등을 유포하는 광고성 블로거", 0);
            mDbOpenHelper.insertColumn("IP 스푸핑(IP Spoofing)",
                    "공격자가 자신의 인터넷 프로토콜(IP) 주소를 변조하거나 속여서 접근 제어 목록(ACL: Access Control List)을 우회하거나 회피하여 공격하는 것." +
                            " 변조된 IP 주소를 이용하여 서비스 거부 공격이나 연결된 세션을 차단하며 공격에 대한 추적을 어렵게 만든다.", 0);
            mDbOpenHelper.insertColumn("스니핑(Sniffing)",
                    "네트워크의 중간에서 남의 패킷 정보를 도청하는 해킹 유형의 하나. 수동적 공격에 해당하며, 도청할 수 있도록 설치되는 " +
                            "도구를 스니퍼(Sniffer)라고 한다. (몰래 훔쳐봄)", 0);
            mDbOpenHelper.insertColumn("스위치 재밍(Switch Jamming)",
                    "위조된 MAC 주소를 네트워크 상으로 지속적으로 흘려보내 스위칭 허브의 주소 테이블 기능을 마비시키는 것", 0);
            mDbOpenHelper.insertColumn("스팸(Spam)",
                    "인터넷상에서 다수의 수신인에게 무작위로 송신된 이메일 메시지, 또는 다수의 뉴스그룹에 일제히 게재된 뉴스 기사. 우편을 통해 불특정 다수인에게" +
                            " 무더기로 발송된 광고나 정크 메일과 같은 의미", 0);
            mDbOpenHelper.insertColumn("사이버 스토킹(Cyber Stalking)",
                    "정보통신망을 이용해 악의적인 의도로 지속적으로 공포감이나 불안감 등을 유발하는 행위", 0);
            mDbOpenHelper.insertColumn("피싱(Phishing)",
                    "허위 웹 사이트를 내세워 사용자의 개인 신용 정보를 빼내는 수법", 0);
            mDbOpenHelper.insertColumn("반달리즘(Vandalism)",
                    "다수가 참여할 수 있도록 공개된 문서의 내용을 훼손하거나 엉뚱한 제목으로 변경하고 낙서를 하는 일", 0);
            mDbOpenHelper.insertColumn("스누핑(Snooping)",
                    "네트워크상에서 남의 정보를 염탐하여 불법으로 가로채는 행위. (몰래 획득)", 0);
            mDbOpenHelper.insertColumn("다이어 악성코드(Dyre Malware)",
                    "사용자 컴퓨터에 악성코드를 설치하는 트로이목마의 한 종류. 주로 윈도우(Windows) 운영 체제를 사용하는 금융 기관을 대상으로 전자우편(이메일)" +
                            " 첨부 파일을 통해 악성코드를 유포한다. 첨부 파일을 실행시키면 악성 프로그램이 설치되거나 가짜 웹사이트로 접속되어 금융 정보가 유출된다.", 0);
            mDbOpenHelper.insertColumn("드롭퍼(Dropper)",
                    "정상적인 파일 등에 트로이 목마나 웜, 바이러스가 숨겨진 형태를 일컫는 말", 0);
            mDbOpenHelper.insertColumn("메모리 해킹(Memory Hacking)",
                    "컴퓨터 메모리(주기억장치)에 있는 데이터를 위,변조하는 해킹방법으로, 정상적인 인터넷뱅킹 사이트를 이요했음에도 이체거래 과정에서 수취인의" +
                            " 계좌번호를 변조하거나 보안카드의 비밀번호를 빼내어 돈을 빼돌린다.", 0);
            mDbOpenHelper.insertColumn("스미싱(Smishing)",
                    "문자 메시지(SMS)와 피싱(Phishing)의 합성어로, 무료쿠폰이나 모바일 초대장 등의 문자 메시지를 보낸 후 메시지에 있는 인터넷 주소를 클릭하면" +
                            " 악성코드를 설치하여 개인 금융 정보를 빼내는 행위", 0);
            mDbOpenHelper.insertColumn("스파이웨어(Spyware)",
                    "적절한 사용자 동의 없이 사용자 정보를 수집하는 프로그램 또는 적절한 사용자 동의 없이 설치되어 불편을 야기하거나 사생활을 침해할 수 있는 프로그램", 0);
            mDbOpenHelper.insertColumn("웜(Worm)",
                    "네트워크를 통해 연속적으로 자신을 복제하여 시스템의 부하를 높힘으로써 결국 시스템을 다운시키는 바이러스의 일종. 분산 서비스 거부 공격" +
                            ", 버퍼 오버플로 공격, 슬래머 등이 한 형태이다.", 0);
            mDbOpenHelper.insertColumn("크래킹(Cracking)",
                    "어떤 목적을 가지고 타인의 시스템에 불법으로 침입하여 정보를 파괴하거나 정보의 내용을 자신의 이익에 맞게 변경하는 행위", 0);
            mDbOpenHelper.insertColumn("트로이 목마(Trojan Horse)",
                    "정상적인 기능을 하는 프로그램으로 가장하여 프로그램 내에 숨어 있다가 해당 프로그램이 동작할 때 활성화되어 부작용을 일으키는 것으로, 자기 " +
                            "복제 능력은 없다.", 0);
            mDbOpenHelper.insertColumn("해킹(Hacking)",
                    "컴퓨터 시스템에 불법적으로 접근, 침투하여 시스템과 데이터를 파괴하는 행위", 0);
            mDbOpenHelper.insertColumn("혹스(Hoax)",
                    "실제로는 악성 코드로 행동하지 않으면서 겉으로는 악성 코드인 것처럼 가장하여 행동하는 소프트웨어", 0);


            // 그 외 최근 IT 용어들 (page1)
            mDbOpenHelper.insertColumn("분기(Branch)",
                    "컴퓨터 프로그램에서 순차적 제어 흐름을 조건에 따라 다른 흐름으로 변경하거나, 기계어 명령어 체계에서 지시된 주소로 명령 순서를 이동하는 것", 0);
            mDbOpenHelper.insertColumn("에크마스크립트(ECMAScript)",
                    "웹 페이지에서 사용자가 입력한 이벤트를 받아 동적 처리를 하는 데 사용", 0);
            mDbOpenHelper.insertColumn("Hybrid app(Hybrid mobile application)",
                    "모바일 웹과 기존 네이티브 앱의 장점을 모두 가진다.", 0);
            mDbOpenHelper.insertColumn("모바일 백홀망(Mobile backhaul network)",
                    "이동 통신망에서 기지국과 핵심망을 연결하는 망. LTE와 5세대(5G) NR 등에서 사용자 단말의 데이터를 기지국에서 모아서 핵심망(백본망)에 전달하는 환경을 의미", 0);
            mDbOpenHelper.insertColumn("mmWave(millimeter wave)",
                    "주파수가 30~300 기가헤르츠(㎓)이고, 파장이 1~10 밀리미터(mm)인 전파. 극고주파(EHF: Extremely High Frequency)를 파장으로 구분하여 부르는 명칭이다.\n" +
                            "※ 밀리미터파의 높은 경로 손실과 직진성 등으로 인해 이동 통신 분야에서는 본격적으로 활용되지 않았으나 넓은 대역폭을 확보할 수 있다는 이점으로" +
                            " 인해 5세대 이동 통신(5G)의 초광대역 서비스에 사용될 후보 대역으로 선정되었다. 주로 100GHz 이하의 대역을 5G 밀리미터파 대역으로 고려한다.", 0);
            mDbOpenHelper.insertColumn("백홀(Backhaul)",
                    "다수의 통신망을 통해 데이터를 전송하는 계층적 구조로 된 통신망에서 주변부 망(edge|com network)을 기간 망(backbone network)이나 인터넷에 연결시키는 링크", 0);
            mDbOpenHelper.insertColumn("WebGL(Web-based Graphics Library)",
                    "구글 크롬, 애플 사파리, 모질라 파이어폭스 등 모든 웹 브라우저 환경에서 사용될 수 있고 저작권이 없는 웹 표준이다." +
                            " 3D 컴퓨터 그래픽스 응용 프로그래밍 인터페이스인 '임베디드 시스템용 오픈지엘(OpenGLES)'의 " +
                            "주요 기능을 웹 브라우저에 내장하고 일대일로 제어할 수 있는 API를 자바스크립트 라이브러리 표준으로 구성한 것이다.", 0);
            mDbOpenHelper.insertColumn("RDFa(Resource Description Framework in attributes)",
                    "이는 기존 웹 문서에 비해 특정 태그 안에 삽입된 데이터가 어떤 뜻을 담고 있는지를 표현할 수 있어, 수많은 웹 페이지를 속성에 따라 효율적으로 색인할 수 " +
                            "있고 서로 다른 서비스 간에도 데이터 연동을 할 수 있게 한다.", 0);
            mDbOpenHelper.insertColumn("지능형 사물(Intelligent things)",
                    "빛, 열, 음, 전파 등 다양한 센서로 주변 상황을 인지하여 획득한 정보를 통신망을 통해 컴퓨터에 전달하고, 지능 정보 처리된 결과를" +
                            " 다시 받아 컨트롤러를 통해 스스로 주변 환경에 대응한다.", 0);
            mDbOpenHelper.insertColumn("에지 컴퓨팅(Edge computing)",
                    "다양한 단말 기기에서 발생하는 데이터를 클라우드와 같은 중앙 집중식 데이터센터로 보내지 않고 데이터가 발생한 현장 혹은 근거리에서 실시간 " +
                            "처리하는 방식으로 데이터 흐름 가속화를 지원하는 컴퓨팅 방식", 0);
            mDbOpenHelper.insertColumn("HEIF(High Efficiency Image File Format)",
                    "엠펙(MPEG)에서 고효율 비디오 부호화(HEVC) 표준 기반으로 만든 이미지 파일 형식. 기존 제이펙(JPEG) 이미지 파일 형식보다 " +
                            "이미지 파일 용량을 2배 가량 줄이면서도 동일 수준 이상의 품질을 제공한다.", 0);
            mDbOpenHelper.insertColumn("퍼즈 시험(Fuzzing)",
                    "소프트웨어의 취약점을 찾기 위해 무작위로 데이터를 입력하여 예외 오류를 발생시킨 후 원인을 분석하는 시험. 주로 프로그램 충돌이나 소스 코드 내의 " +
                            "오류, 잠재적인 메모리 누수와 같은 예외적인 상황을 찾을 때 사용한다.", 0);
            mDbOpenHelper.insertColumn("물리 컴퓨팅(Physical computing)",
                    "현실 세계의 아날로그 정보를 인지하여 그에 맞게 대응할 수 있도록 센서와 마이크로컨트롤러 등의 하드웨어 장치와 소프트웨어로 컴퓨팅 시스템을 만드는 것." +
                            " 넓게는 사람이 디지털 세계를 이해하고 창의적으로 활용하기 위한 프레임워크라고 볼 수 있으며, 좁게는 소프트웨어와 하드웨어 부품 등으로 기기를 " +
                            "직접 제작하는 것을 의미한다.", 0);
            mDbOpenHelper.insertColumn("AGI(Artificial General Intelligence)",
                    "특정 문제뿐 아니라 주어진 모든 상황에서 생각과 학습을 하고 창작할 수 있는 능력이 있는 인공 지능 또는 이에 대한 연구. 인공 지능 연구의 궁극적 목표 중 하나이다." +
                            " 특정 문제만을 해결할 수 있는 인공 지능은 좁은 인공 지능(ANI: Artificial Narrow Intelligence)이라 한다.", 0);
            mDbOpenHelper.insertColumn("CT(Computational Thinking)",
                    "컴퓨터로 처리할 수 있는 형태로 문제와 해결책을 표현하는 사고 과정. 컴퓨터에게 뭘 해야 할지를 사람이 설명해 주는 것이라고 볼 수 있다.", 0);


            // 그 외 최근 IT 용어들 (page2)
            mDbOpenHelper.insertColumn("마이크로비트(micro:bit)",
                    "영국 방송 공사(BBC)에서 쉽고 재미있는 컴퓨터 교육을 위해 만든 암(ARM) 기반의 소형 싱글 보드 컴퓨터", 0);
            mDbOpenHelper.insertColumn("E-UTRA(Evolved UMTS(Universal Mobile Telecommunications System) Terrestrial Radio Access, Evolved UTRA)",
                    "엘티이(LTE) 이동 통신에서 무선 접속망(E-UTRAN)을 구성하는 단말과 기지국 사이의 무선 접속 기술", 0);
            mDbOpenHelper.insertColumn("E-UTRAN(Evolved UMTS(Universal Mobile Telecommunications System) Terrestrial Radio Access Network, Evolved UTRAN)",
                    "엘티이(LTE) 이동 통신의 무선 접속망", 0);
            mDbOpenHelper.insertColumn("FWA(Fixed Wireless Access)",
                    "주로 건물 외부에 설치되는 가입자 단말과 기지국 사이는 광케이블 같은 유선으로 연결되는 것이 일반적인데, 유선 구간 일부를 광대역 무선 " +
                            "통신으로 대체하여 초고속 인터넷 환경을 제공한다.", 0);
            mDbOpenHelper.insertColumn("프론트홀(Fronthaul)",
                    "기지국의 디지털 데이터 처리 장치(DU)를 분리시켜 여러 DU를 한 장소에 모아 관리하고, 기지국에는 무선 신호 처리 장치(RU)만 설치하여 분산시킨" +
                            " 무선 접속망을 클라우드 무선 접속망(C-RAN: Cloud Radio Access Network)이라 한다. 이러한 C-RAN 구조에서 이는 DU 사이트와 RU를 연결하는 데이터 링크이다.", 0);
            mDbOpenHelper.insertColumn("EPC(Evolved Packet Core)",
                    "엘티이(LTE) 이동 통신의 핵심망. 3세대(3G) 이동 통신의 핵심망과 가장 크게 다른 점은 패킷 핵심망(packet core network)만으로 구성된다는 것이다." +
                            " 3G 경우 서킷 핵심망(circuit core network)에서 음성 서비스를 처리하고 패킷 핵심망에서 데이터 서비스를 처리한다.", 0);
            mDbOpenHelper.insertColumn("5G(5th Generation)",
                    "국제 전기 통신 연합(ITU)에서 정한 공식 명칭은 아이엠티 2020(IMT-2020)이지만, 이동 통신 시장에서는 세대로 구분하여 이 용어를 사용", 0);
            mDbOpenHelper.insertColumn("WA(Wasm, WebAssembly)",
                    "웹 페이지에서 실행할 코드에 대해 이진 포맷과 어셈블리와 유사한 텍스트 포맷을 정의한 웹 표준. 웹 응용 프로그램의 실행 성능과 하드웨어 이식성이 높다.", 0);
            mDbOpenHelper.insertColumn("노드닷제이에스(Node.js)",
                    "서버 쪽 응용 프로그램 개발에 사용할 수 있는 자바스크립트 기반의 소프트웨어 플랫폼. '모든 곳에서 자바스크립트 사용(JavaScript everywhere)'" +
                            "이라는 비전에 따라 만들어져, 자바스크립트 언어 하나만으로 웹 클라이언트 응용 프로그램과 서버 응용 프로그램 모두 개발할 수 있게 되었다.", 0);
            mDbOpenHelper.insertColumn("깃허브(GitHub)",
                    "컴퓨터 프로그램 소스를 공유하고 협업하여 개발할 수 있는 버전 관리 시스템인 깃(Git)에 프로젝트 관리 지원 기능을 확장하여 제공하는 웹 호스팅 서비스", 0);
            mDbOpenHelper.insertColumn("감정 그림 문자(Emoji)",
                    "감정을 표현하는 유니코드의 그림 문자 처리 기술", 0);
            mDbOpenHelper.insertColumn("감정 스티커(Emoticon sticker)",
                    "캐릭터의 다양한 애니메이션 동작을 이용하여 감정을 표현하는 그림 문자 처리 기술 또는 그 캐릭터", 0);
            mDbOpenHelper.insertColumn("탈중앙화 웹(Decentralized web)",
                    "웹상에서 특정 기업이나 기관이 사용자의 데이터를 독점하는 현상에 대응하여 데이터의 분산 저장과 사용자의 데이터 접근 이용 정책을 개방하는 현상을 일컫는 말", 0);
            mDbOpenHelper.insertColumn("중앙화 웹(Centralized web)",
                    "웹상의 데이터를 특정 중앙 서버에 저장하여 처리하거나 특정 기업이나 기관이 사용자의 데이터를 독점하는 현상을 일컫는 말", 0);


            // 그 외 최근 IT 용어들 (page3)
            mDbOpenHelper.insertColumn("SMV display(Super Multi-View display)",
                    "시점 간의 간격이 동공 크기보다 작은 디스플레이. 시점 간의 간격이 조밀하여 한쪽 눈에 (초)다시점 영상이 동시에 투사된다. " +
                            "양안 시차뿐만 아니라 눈의 초점도 정확한 지점(깊이면)에 맺히도록 조절하며, 무안경(glasses-free)식의 3D 디스플레이에 활용된다.", 0);
            mDbOpenHelper.insertColumn("3DOF(three Degrees Of Freedom)",
                    "삼차원 공간에서의 운동하는 물체의 세 가지 회전 동작", 0);
            mDbOpenHelper.insertColumn("상호 작용 서비스(interactive service)",
                    "유무선 통신 기기로 사용자와 호스트가 정보를 주고받는 서비스. 사용자는 와이파이, 이동통신망 등 IP 망에서 스마트폰, IPTV 등 기기를 통해" +
                            " 원하는 비디오, 음악, 방송 프로그램 등 다양한 콘텐츠를 찾아 이용할 수 있다.", 0);


            // 그 외 최근 IT 용어들 (page4)
            mDbOpenHelper.insertColumn("ASO(App Store Optimization)",
                    "모바일 앱 스토어 내에서 모바일 앱 홍보를 최적화하는 방법", 0);
            mDbOpenHelper.insertColumn("객체 기반 오디오(object-based audio)",
                    "오디오 신호를 개별적인 객체 단위의 음원들과 각 음원 객체의 위치, 크기 등 정보를 포함하는 메타데이터로 나누어 기술하는 방법", 0);
            mDbOpenHelper.insertColumn("표정 추적(facial expression tracking)",
                    "얼굴 표정을 인식하기 위해 눈, 코, 입 등 얼굴의 주요한 특징점을 센싱하여 그 위치 변화를 추적하는 기술", 0);
            mDbOpenHelper.insertColumn("MWCNT(Multi-Walled Carbon NanoTube, MWNT)",
                    "벽이 두개 이상인 탄소 나노튜브. 열이나 전기를 전하는 성질이 좋고 길게 늘어나는데다 단단하고, 전기·열 전도율이 구리·다이아몬드와 비슷하며," +
                            " 강도는 철강의 100배쯤 된다. 단일벽보다 물성이 떨어지나 기계적 특성이 우수하여 대량 합성이 용이하다.", 0);
            mDbOpenHelper.insertColumn("재난 방송(Disaster Broadcasting)",
                    "재난이 발생하거나 발생할 우려가 있는 경우 재난의 예방, 수습과 복구를 위해 실시하는 방송", 0);
            mDbOpenHelper.insertColumn("색 재현율(color gamut)",
                    "디스플레이의 색 표현 영역을 CIE 표준 측색 시스템을 기준으로 면적을 비교하여 백분율로 표시한 값\n" +
                            "※ 디스플레이 성능 지표: 해상도, 명암비, 휘도, 색 재현율", 0);
            mDbOpenHelper.insertColumn("PCI-DSS(Payment Card Industry - Data Security Standard)",
                    "지불 카드 소유자의 개인정보, 거래 정보 등을 안전하게 보호하기 위해 지불 카드 결제의 모든 과정에 필요한 보안 요구 사항을 규정한 국제 데이터 보안 표준", 0);
            mDbOpenHelper.insertColumn("e-sports(electronic sports)",
                    "온라인상에서 겨루는 게임을 통틀어 가리키는 용어", 0);
            mDbOpenHelper.insertColumn("XaaS(Everything as a Service, aaS)",
                    "사용자에게 필요한 소프트웨어, 콘텐츠, 스토리지 등을 인터넷상에서 대여 형태로 제공하는 클라우드 컴퓨팅 서비스 시스템", 0);
            mDbOpenHelper.insertColumn("SWCNT(Single-Walled Carbon NanoTube, SWNT, 단일벽 탄소 나노튜브)",
                    "벽이 하나인 탄소 나노튜브. 여러 겹의 탄소 구조들로 이루어진 다중벽에 비해 투명도와 물성이 우수하며 전도성 필름, 고분자 화합물, 금속 화합물 등" +
                            " 응용 분야가 넓고 가격이 비싸 부가 가치가 높다.", 0);
            mDbOpenHelper.insertColumn("오픈스택(OpenStack)",
                    "클라우드 컴퓨팅 플랫폼을 개발하고 관리할 수 있는 공개 소스 소프트웨어 기반의 클라우드 운영 체제. 서비스형 인프라스트럭처(IaaS: Infrastructure as a Service)를" +
                            " 쉽게 구축할 수 있는 플랫폼", 0);
            mDbOpenHelper.insertColumn("e-skin(electronic Skin, 전자 피부)",
                    "사람의 피부와 같은 기능을 가지도록 얇은 전자 소재로 만든 인공 피부. 인체의 피부에 붙이거나 체내에 내장해 온도, 습도, 압력 등의 외부 자극을 감지한다.", 0);
            mDbOpenHelper.insertColumn("CNT FEA(Carbon NanoTube Field Emission Array, 탄소 나노튜브 전계 방출 어레이)",
                    "전계 방출 평판 표시 소자의 전자 방출원으로 적용하기 위해 탄소 나노튜브(CNT)를 다수 조합하여 구성한 평면 어레이", 0);


            // 그 외 최근 IT 용어들 (page5)
            mDbOpenHelper.insertColumn("비디오 업스케일링(video upscaling)",
                    "저해상도로 제작된 비디오를 고해상도로 개선하는 기술", 0);
            mDbOpenHelper.insertColumn("CNT FEL(Carbon NanoTube Field Emission Lamp)",
                    "음극 기판에 형성된 탄소 나노튜브 전계 에미터에 전기장을 인가하여 방출된 전자를 고전압으로 가속하여 양극 기판의 형광체에 " +
                            "충돌시켜 발광하는 음극선 발광형 면광원. 탄소 나노튜브 백 라이트 유닛(CNT BLU)으로 사용된다.", 0);
            mDbOpenHelper.insertColumn("CNT BLU(Carbon NanoTube BackLight Unit)",
                    "고속 순간 발광에 의한 잔상 제거 같은 장점으로 고화질 TV용 디스플레이에 활용되는 LCD 디스플레이.", 0);
            mDbOpenHelper.insertColumn("FaaS(Functions as a Service)",
                    "애플리케이션 개발자에게 함수 단위의 소스 코드를 포함하여 애플리케이션의 개발, 실행, 관리 등에 필요한 모든 환경을 갖춘 플랫폼을" +
                            " 제공하는 클라우드 컴퓨팅 서비스. 서버가 없는 컴퓨팅(serverless computing)이라고도 한다.", 0);
            mDbOpenHelper.insertColumn("TPC(Transmit Power Control)",
                    "무선기기의 송신 전력을 통신 환경에 맞게 동적으로 제어하는 기술", 0);


            // 업무 프로세스
            mDbOpenHelper.insertColumn(" ISP(Information Strategy Planning)",
                    " 조직, 기관의 미래상을 달성하기 위하여 어떻게 효과적으로 정보기술을 연계하고 적용할 것인가 전략을 짜고 해결책을 찾아 실행 계획을 수립해 나가는 일련 과정", 0);
            mDbOpenHelper.insertColumn("3C 분석",
                    "기업이 표적 시장을 선정하기 위해 고객(Customer)의 요구, 경쟁사(Competitor)와의 경쟁력, 자사(Company)의 역량과 기존 사업의 시너지 여부 등을 분석하여 " +
                            "자사의 성공에 필요한 전략을 수립하는 기법. 유통(Channel)을 포함하여 4C라고도 한다. 오마에 겐이치가 제안", 0);
            mDbOpenHelper.insertColumn("SWOT 분석",
                    "기업의 내부적인 요인 중 강점(Strength)은 부각, 약점(Weakness)은 보완, 외부적인 요인 중 기회(Opportunity)는 활용," +
                            " 위협(Threat)은 억제 및 회피하는 마케팅 전략을 수립하는 기법. 알버트 험프리가 고안", 0);
            mDbOpenHelper.insertColumn("5-FORCE 분석",
                    "기업에 영향을 미치는 외부의 5가지 요인을 분석하여 기업의 경쟁 환경을 분석하기 위한 도구. 마이클 포터가 제안\n" +
                            "- 신규 진입자의 위협(Threat of new entrants)\n" +
                            "- 기존 업계 경쟁(Industry rivalry)\n" +
                            "- 공급자의 교섭력(Bargaining power of suppliers)\n" +
                            "- 구매자의 교섭력(Bargaining power of customers)\n" +
                            "- 대체품의 위협(Threat of substitutes)", 0);
            mDbOpenHelper.insertColumn("7S 분석",
                    "조직의 강점과 약점, 조직 문화 등 전체적인 관점에서 조직을 이해하고 분석하기 위한 도구.\n" +
                            "- 전략(Strategy) 조직의 목표와 방향을 설정하고 목표 달성을 위한 효과적인 자원 배치\n" +
                            "- 시스템(System) : 조직의 관리 및 경영을 위한 모든 절차 및 프로세스\n" +
                            "- 구조(Structure) : 조직 구성원들의 역할과 권한, 책임 등 상호 관계를 규정하는 요소들\n" +
                            "- 공유된 가치(Shared value) : 조직이 믿고 있는 대표적인 신념이나 태도로, 조직의 존속과 성공에 결정적이라고 믿는 것\n" +
                            "- 기술/역량(Skill) : 조직이 내부적으로 보유하고 있는 핵심 역량과 능력\n" +
                            "- 사람(Staff) : 조직 내부의 인적 자원 보유 능력이나 특성\n" +
                            "- 스타일(Style) : 조직 내부에 오랫동안 형성된 경영 방식\n" +
                            "\n" +
                            "- Hard 3S : 전략, 구조, 시스템\n" +
                            "- Soft 4S : 공유된 가치, 기술/역량, 사람, 스타일\n" +
                            "\n" +
                            "- 상위 3S : 전략, 공유된 가치, 기술/역량\n" +
                            "- 하위 4S : 시스템, 구조, 사람, 스타일", 0);
            mDbOpenHelper.insertColumn("BPR(Business Process Reengineering)",
                    "비용, 품질, 서비스, 속도와 같은 핵심적 부분에서 극적인 성과를 이루기 위해 기업 업무 프로세스를 기본적으로 다시 " +
                            "생각하고 근본적으로 재설계하는 것. 모든 부분에 걸쳐 개혁을 하는 것이 아니라 중요한 비즈니스 프로세스들" +
                            ", 즉 핵심프로세스를 선택하여 그것들을 중점적으로 개혁해 나가는 것이다.\n" +
                            "1단계 : 현행 업무 프로세스(As-Is) 모형 분석\n" +
                            "2단계 : 개선 사항 분석\n" +
                            "3단계 : 향후 업무 프로세스(To-Be)모형 수립\n" +
                            "4단계 : 이행 계획(Transition Plan)", 0);
            mDbOpenHelper.insertColumn("BPM(Business Process Management)",
                    "생산, 영업, 재무관리 등 기업업무 프로세스를 효율적으로 관리하기 위한 솔루션. 기업의 업무 흐름을 한 눈에 볼 수 있도록" +
                            " 만들어 인력과 시스템을 적절하게 투입하고 통제하는 프로세스 관리가 핵심이다. 즉, 프로세스 관리를 통해 생산성을 향상하고" +
                            " 내부의 역량을 강화하며 궁극적으로 경쟁력을 확보할 수 있다는 관점에서 출발한다.", 0);
            mDbOpenHelper.insertColumn("RTE(Real-Time Enterprise)",
                    " 기업 내,외부에 걸친 지속적인 프로세스의 개선과 실시간 정보제공을 통해 업무지연을 최소화하고 의사결정 스피드를 높여 경쟁력을 극대화하는 기업", 0);
            mDbOpenHelper.insertColumn("PI(Process Innovation)",
                    "회사의 업무 프로세스, 조직, IT등 기업 활동의 전 부문에 걸쳐 불필요한 요소들을 제거하고 효과적인" +
                            " 업무 프로세스를 재구축함으로써 기업 가치를 극대화하는 경영개선 업무. 고객 중심, 하급 업무 수행자의 권한을 강화하는 것 등에 관점을 둔다.", 0);
            mDbOpenHelper.insertColumn("ERP(Enterprise Resource Planning)",
                    "기업 내 통합정보시스템을 구축하는 것. 자재 소요 계획(MRP, Material Requirement Planning)과" +
                            " 제조 자원 계획(MRP 2, Manufacturing Resource Planning)보다 개념과 기능이 크게 향상된 것이다.\n" +
                            "- 가트너 그룹의 정의 : 제조 업무 시스템을 핵심으로 재무회계와 판매, 그리고 물류 시스템 등을 통합한 것으로 가상 기업을 지향하는 시스템", 0);
            mDbOpenHelper.insertColumn("ERP 패키지",
                    "ERP를 실현하기 위해서 공급되고 있는 소프트웨어. 데이터를 어느 한 시스템에서 입력하면 전체적으로 자동 연결되어 별도로 Interface를 처리해야 할 필요가 없는 통합 운영이 가능한 시스템", 0);
            mDbOpenHelper.insertColumn("SEM(Strategic Enterprise Management)",
                    "경영자 및 관리자들이 기업의 수익 창출을 위한 합리적인 의사 결정을 할 수 있도록 신뢰할 수 있는 정보를 제공해 주는 전략적 의사결정 지원 시스템." +
                            " 즉, 기업의 경영 정보를 좀 더 정확히 판단해 최고경영진과 임원으로 하여금 가치 중심의 경영을 전사적으로 구현할 수 있게 해주는 일련의 통합 경영관리 체계", 0);
            mDbOpenHelper.insertColumn("IRM(Information Resource Management)",
                    " 조직에 필요한 정보를 생산하는 데 사용되는 자원을 관리하는 것. 구성요소 3가지로 정보, 정보기술, 정보시스템이 있다.", 0);
            mDbOpenHelper.insertColumn("ERM(Enterprise Risk Management)",
                    " 기업의 궁극적인 목표 달성을 위해 기업이 직면하고 있는 주요 리스크들을 식별하고 관리하기 위한 리스크 관리 방식", 0);
            mDbOpenHelper.insertColumn("SCM(Supply Chain Management)",
                    " 기업 간 또는 기업 내부에서 제품이나 부품의 최초 생산자부터 최종 소비자에 이르는" +
                            " 공급량을 효율적으로 관리해 불필요한 시간과 비용, 재고 등을 줄이려는 관리 기법. 공급망 계획" +
                            "(SCP, Supply Chan Planning) 시스템과 공급망 실행(SCE, Supply Chan Execution) 시스템으로 구성되어 있다.\n" +
                            "- e-SCM : 웹상에서도 기존의 SCM 관련 기술들을 수행할 수 있도록 한 것", 0);
            mDbOpenHelper.insertColumn("PLM(Product Lifecycle Management)",
                    " 기업이 제품의 원가를 낮추고 부가가치를 높일 수 있도록 기획부터 설계, 생산, 서비스, 폐기에 이르는 수명주기를 관리하는 것", 0);
            mDbOpenHelper.insertColumn("CRM(Customer Relationship Management)",
                    " 고객의 요구나 의견을 분석하여 고객의 성향과 요구를 충족시키는 마케팅을 실시하므로 고객 관리 비용을 최소화하고 지속적인 기업의 " +
                            "가치 창출을 위해 등장. 집중 공략형 영업전략. IBM사가 창안한 마케팅전략이다. 예를 들어 1건의 영업 프로젝트가 있을 경우 사전에 고객의" +
                            " 구매의지를 평가하고 이중 가능성이 있는 대여섯 건을 골라낸 후 사내에 흩어져 있는 전문 인력을 프로젝트별로 투입하여 인력낭비를 최소화하고" +
                            " 시너지효과를 발휘하게 하는 새로운 경영기법이다. 신규 고객 유도, 장기 고객 유지.", 0);
            mDbOpenHelper.insertColumn("SRM(Supplier Relationship Management)",
                    " 제품을 공급하는 공급업체와의 관계를 관리하고 평가하여 기업의 수익성을 높일 수 있도록 해주는 경영 기법", 0);
            mDbOpenHelper.insertColumn("MDM(Master Data Management)",
                    " 기업의 내,외부에 산재해 있는 마스터 데이터의 단일화를 통해 활용도를 높이고 오류를 줄이기 위한 모든 활동의 집합. 데이터 품질이 중요. PIM(Product Information Management, 제품 정보 관리)과 CDI(Customer Data Integration, 고객 데이터 통합)로 구성\n" +
                            "- 마스터 데이터 : 고객, 제품, 영업, 공급업체, 서비스 등 기업 비즈니스에서 반드시 필요한 핵심 데이터", 0);
            mDbOpenHelper.insertColumn("VRM(Vendor Relationship Management)",
                    " 개인이 기업에게 제공할 개인 정보, 선호도, 패턴 등을 관리하는 기술로, 기업이 고객의 정보를 분석 및 통합하여 " +
                            "관리하는 CRM에 반대되는 개념. 개인이 자신의 제안사항을 기업들에게 보내면, 기업들은 제안사항을 검토하고 적당한" +
                            " 조건을 책정하여 제시한다. 그러면 개인은 자신에게 맞는 조건을 제시한 기업을 선택하는 역경매 구조가 가능하다.", 0);
            mDbOpenHelper.insertColumn("EAI(Enterprise Application Integration)",
                    " 기업 내 상호 연관된 모든 플랫폼(DBMS, OS 등) 및 애플리케이션(ERP, SCM, CRM 등)을 유기적으로 연동하여" +
                            " 필요한 정보를 중앙 집중적으로 통합, 관리, 사용할 수 있는 환경을 구현하는 것으로 e-비즈니스를 위한 기본 인프라" +
                            ". 기존의 점 대 점 인터페이스에서는 애플리케이션 수의 실질적 한계와 유지 보수의 어려움 및 애플리케이션 추가 " +
                            "시 방대한 비용 및 시간 손실이 있었으나 EAI를 도입한 인터페이스에서는 새로운 애플리케이션 도입 시 어댑터만" +
                            " 필요한 손쉬운 확장성이 보장된다. 다음 4가지로 구성됨.\n" +
                            "- 어댑터(Adapter) : 어플리케이션과 EAI 연결 중개. 소프트웨어와 플랫폼 사이에서 인터페이스를 담당\n" +
                            "- 플랫폼(Platform) : 시스템 간에 안전한 데이터 전송을 보장\n" +
                            "- 브로커(Broker) : 전송 데이터의 포맷팅 및 라우팅 지원\n" +
                            "- 업무흐름(Workflow) : EAI 솔루션 관리 및 인터페이스 데이터의 상태 모니터링", 0);
            mDbOpenHelper.insertColumn("EIP(Enterprise Information Portal)",
                    "기업 내부의 그룹웨어, 인트라넷, 텍스트파일 이메일 등의 데이터는 물론 주식, 뉴스 등" +
                            " 외부 정보까지 모두 통합해 하나의 웹 화면으로 제공하는 통합 정보시스템. e-포털. 통합된 정보 제공이 목적", 0);
            mDbOpenHelper.insertColumn("EKP(Enterprise Knowledge Portal)",
                    " 한 기업의 내,외부 정보를 통합하고 그룹웨어, KMS(지식 관리 시스템), EDMS(전자문서 관리 시스템), " +
                            "인사관리, 업무관리, 업무용 오피스 등 기업에서 사용하는 모든 프로그램을 단일 웹 화면에서" +
                            " 사용하도록 제공하는 시스템. 지식의 추출, 변환, 재활용이 목적", 0);
            mDbOpenHelper.insertColumn("KMS(Knowledge Management System)",
                    " 기업 내 조직구성원들의 다양한 개인적 경험 중에서 다른 이들도 사용할 수 있는 즉, 일반화될 수 있는 경험들을 다른 이들이 활용할 " +
                            "수 있는 형태로 변환하여 공유할 수 있도록 지원하는 시스템", 0);
            mDbOpenHelper.insertColumn("EA(Enterprise Architecture)",
                    " 기업의 목표와 요구사항을 효과적으로 지원하기 위해 IT 인프라 각 부분의 구성과 구현 기술을 체계적으로 기술하는 작업", 0);
            mDbOpenHelper.insertColumn("BI(Business Intelligence)",
                    "기업 내부에 축적된 대량의 정보를 기업의 경영활동 전번에 활용될 수 있도록 정리, 분석하여 제공하는 애플리케이션과 기술을 총칭. 밀러 데븐스에 의해 처음 사용된 용어\n" +
                            "- BI에서 활용되는 업무 분석 기법 : 데이터 웨어하우스, 데이터 마트, 데이터 마이닝, OLAP, OLTP", 0);
            mDbOpenHelper.insertColumn("BRMS(Business Rule Management System)",
                    " 기업 정책, 가격 정책, 마케팅 전략, 이벤트 등 기업 운영에 사용되는 비구조적이고" +
                            " 복잡한 업무 프로세스를 자동으로 정의 및 제어함으로써 경험이 부족한 비전문가도 효과적인 업무 처리 및 상품 판매 등이 가능하도록 지원하는 시스템", 0);
            mDbOpenHelper.insertColumn("BPO(Business Process Qutsourcing)",
                    " 기업의 경쟁력 강화를 위해 핵심 업무를 제외한 모든 업무를 외부 전문 기업에 위탁하여 자사의 핵심 업무에 역량을 집중하는 경영 기법을 의미\n" +
                            "- ITO(Information Technology OutSourcing, 정보 기술 아웃소싱) : IT 관련 업무를 위탁", 0);
            mDbOpenHelper.insertColumn("SLA(Service Level Agreement)",
                    "서비스 시간, 가용성, 성능, 복구 등 일정한 서비스 수준을 보장하기 위해 IT 서비스 사업자와 사용자 간에 맺는 계약\n" +
                            "- SLO(Service Level Objective) : 서비스 수준을 표현하기 위한 실제 항목의 기준 및 목표치", 0);
            mDbOpenHelper.insertColumn("SLM(Service Level Management)",
                    " IT 서비스 사업자와 사용자 간에 맺은 SLA에서 정의한 서비스에 대해 성과와 문제점을 정확히 측정, 평가하고 개선해 나아가는 과정", 0);
            mDbOpenHelper.insertColumn("ITSM(IT Service Management)",
                    "서비스 사용자에게 제공되는 IT 서비스의 계획, 설계, 제공, 운영 및 관리를 위해 서비스 제공자가 수행하는 " +
                            "프로세스 및 지원 절차. IT 관리가 IT시스템 자체의 기능 및 기술 사안에 초점을 맞춘데 반해 ITSM에서는 프로세스와 고객에 초점을 맞춘 서비스 관리와 같은 개념이 핵심.", 0);
            mDbOpenHelper.insertColumn("ITIL(IT Infrastructure Library)",
                    " IT 서비스의 운영 및 관리를 돕기 위한 문서들의 집합. 영국 정부가 다양한 IT 서비스들의 관리 방법들을 모아 만든 표준적인 참고 문헌", 0);
            mDbOpenHelper.insertColumn("ISO/IEC 20000",
                    " IT 서비스 관리(ITSM)에 대한 최초의 국제 표준. 영국 표준 협회(BSI)에서 개발한 IT 서비스 관리 표준인 BS 15000을 기반으로 개발", 0);
            mDbOpenHelper.insertColumn("Escrow(조건부 양도증서) 서비스",
                    " 전자상거래 시 판매자와 구매자의 거래가 문제없이 이루어질 수 있도록 제 3자가 도와주는 매매 보호 서비스", 0);


            /*
            mDbOpenHelper.insertColumn("",
                    "", 0);
            */
        }


        main_Cursor = null;
        main_Cursor = mDbOpenHelper.getAllColumns();

        int count_chk = 0;
        while (main_Cursor.moveToNext()) {
            if (main_Cursor.getInt(main_Cursor.getColumnIndex("check_word")) == 0) {
                count_chk++;
            }
        }

        // Shares Preference 수정
        SharedPreferences.Editor ed = prefs.edit();
        ed.putBoolean("key_firstStart", true);
        ed.putInt("key_wordCount", main_Cursor.getCount());
        ed.putInt("key_wordUnchecked", count_chk);
        ed.commit();

        mDbOpenHelper.close();

        Button btn_list = (Button) findViewById(R.id.list);
        Button btn_quiz = (Button) findViewById(R.id.quiz);
        Button btn_quiz2 = (Button) findViewById(R.id.quiz2);
        Button btn_listSet = (Button) findViewById(R.id.listSet);

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSubActivity = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intentSubActivity);
                Log.i("onClick", "ListViewActivity");
            }
        });

        btn_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wordCount > 3 && wordUncheckedCount > 0) {
                    Intent intentSubActivity = new Intent(MainActivity.this, QuizActivity.class);
                    startActivity(intentSubActivity);
                    Log.i("onClick", "QuizActivity");
                } else {
                    Toast toast = Toast.makeText(MainActivity.this, "전체 단어 4개 이상, 남은 단어 1개 이상이어야 합니다.", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        btn_quiz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wordCount > 3 && wordUncheckedCount > 0) {
                    Intent intentSubActivity = new Intent(MainActivity.this, QuizActivity2.class);
                    startActivity(intentSubActivity);
                    Log.i("onClick", "QuizActivity2");
                } else {
                    Toast toast = Toast.makeText(MainActivity.this, "전체 단어 4개 이상, 남은 단어 1개 이상이어야 합니다.", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });


        btn_listSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("onClick", "ListSetActivity");
                Intent intentSubActivity = new Intent(MainActivity.this, ListSetActivity.class);
                startActivity(intentSubActivity);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        wordCount = prefs.getInt("key_wordCount", 0);
        wordUncheckedCount = prefs.getInt("key_wordUnchecked", 0);

        main_tv_wordCount[0].setText("전체 용어 수 : " + wordCount + "개");
        main_tv_wordCount[1].setText("외운 용어 수 : " + (wordCount - wordUncheckedCount) + "개");
        main_tv_wordCount[2].setText("남은 용어 수 : " + wordUncheckedCount + "개");
        Log.i("onClick", "" + wordCount);
        Log.i("onClick", "" + wordUncheckedCount);
    }
}