# Mars Account Service
대학교 전공동아리 Mars의 Account Domain을 기반으로 한 Sample Project입니다.

## Clean Architecture
Server Architecture는 크게 Boundaries, Core, Infra로 나뉘어집니다.

- Boundaries: Controller, gRpcService, Dto, External API Service
- Core: Usecase, Exception, ModelRepository, Model
- Infra: Entity, EntityRepository (with CrudRepository, MongoRepository etc..), ModelRepositoryImpl, Config

### Flow
![temp](https://github.com/meloning/mars-account-service/blob/master/Server%20Clean%20Architecture.png)

Client의 Request가 들어오면, 요청을 전달해주는 역할을 가진 Boundaries 영역에서 요청형태에 따라 gRpc, RestController로 Data를 전달합니다.

이때 요청 Data를 전달받은 Core 영역에서는 비즈니스 로직을 처리하는 Usecase에서 Data를 가공 처리합니다.

비즈니스 흐름에 따라 Data Store가 필요하면, 요청을 통해 전달받은 Data를 Model로 생성하여, interface기반 ModelRepository에게 Data처리를 위한 Message(with crud)를 요청합니다.

Message요청을 받은 ModelRepositoryImpl에서 실제 Data를 저장하기위해 ModelToEntity로 Convert하여, EntityRepository의 Message를 통해 DB에 저장하는 FLow입니다.


핵심 비즈니스로직을 처리하는 책임을 가진 Usecase에선 DB 저장하는 형태가 NoSQL기반인지, RDB기반인지 알 필요가 없게되기때문에 결합도는 낮고, 응집도는 높은 형태라고 볼 수 있습니다. (Open-Closed Principle)

## 참고사항
이 프로젝트는 패키지 기반의 Clean Architecture를 구현한 프로젝트입니다. 하나의 프로젝트이자 모듈에서 라이브러리들을 관리하다보니 의존성 버전 변경에 따른 전파 범위가
프로젝트 전체에 영향이 생길 여지가 있습니다.

또한, 가장 고수준의 도메인 모델이 저수준의 엔티티 모델을 알게되는 문제점이 있습니다.
```kotlin
fun Account.toEntity(): AccountEntity {
    val entity = AccountEntity()
    entity.id = this.id
    entity.userId = this.userId
    entity.passwordHashed = this.passwordHashed
    // detail

    // status
    entity.locked = this.locked
    return entity
}
```
비즈니스 로직을 담고있는 핵심이자 Clean Architecture 구조상 중심부에 있는 도메인 모델이 변할 수 있는 엔티티 모델을 알게되는 것은
구현체가 변할때 같이 영향을 받아 수정되어야 하는 종속성 문제가 생기기 때문에 이를 멀티 모듈로 모듈간 경계선을 강력하게 가져야 합니다.

위 문제점들을 개선해보고자 멀티 모듈기반 사이드 프로젝트를 진행하였습니다.

- [멀티모듈 기반 사이드 프로젝트](https://github.com/meloning/mega-coffee-employee-manage-project)
