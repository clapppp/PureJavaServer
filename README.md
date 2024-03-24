# PureJavaServer

1. 자바 서버, 가장 기초적인 것은 소캣을 직접 열어 통신하는것,
    클라이언트가 IP, PORT를 지정해 소캣을 생성후 연결 요청을 하면 서버에서 accept로 연결해준다.
    클라이언트는 Input,Output stream을 생성해 송수신을 하고 서버도 마찬가지
    통신 끝에는 close로 소캣을 닫는다. try with resource
    클라이언트는 socket 서버는 serversocket으로 관련 없는 클래스들이다.

2. HTTPServer를 이용
    더 발전된 API를 사용해 com.sun.net.httpserver 패키지를 이용하면 좀 더 수월하다. 그리고 위에서는 accept를 사용해 blocking io 였는데
    여기서는 내부적으로 non blocking 을 사용한다.(어떻게 non blocking이 되는지는 모르겠음)
    서버에서는 HTTPServer 인스턴스를 IP,PORT를 인수로 넣어 생성, HTTPHandler를 지정해줘야한다.
    HTTPHandler는 요청 URL에 로직을 매핑해 다른 응답을 보낼수 있다.
    HTTPHandler는 1개의 메서드를 갖는 인터페이스라 람다로 대체할 수 있다.
    그 1개의 메서드가 HTTPExchange를 받아서 이 인스턴스에서 request를 받고 response를 작성한다.
    여기서도 Input , OutputStream을 이용해 송수신을 한다.

2.5?. HTTPServlet
    서블릿 은 HTTP 문자열을 일일히 파싱하는 노력을 덜어주기 위해 사용
    그리고 쓰레드풀을 이용해 많은 요청도 안정적으로 처리한다.(HTTPServer도 쓰레드풀을 이용 할까?)
    HTTPServer은 Handler를 각각 만들어주지만 HttpServlet은 톰캣을 미들웨어로 장착해 매핑을 web.xml파일에서 작성만해주면 톰캣이 알아서 클래스를 호출해준다.

3. spring boot

*. tomcat은 뭘까
    클라이언트와 서버 사이에서 매개체 역할을 하는 프로그램

*. 인스턴스에 toString하면 대부분 sun.net.httpserver.PlaceholderOutputStream@1fe0abd0 이렇게 인스턴스 자체를 설명함

*. 소캣 열때 주소기준은?
    IP에는 공인, 사설이 있다. 공인은 전세계에서 접속 가능한 IP, 사설은 접속이 불가능하다.
    0.0.0.0은 로컬호스트를 뜻하는 것 같다.
    