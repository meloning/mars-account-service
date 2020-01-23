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

핵심 비즈니스로직을 처리하는 책임을 가진 Usecase에선 DB 저장하는 형태가 NoSQL기반인지, RDB기반인지 알 필요가 없게되기때문에 결합도는 낮고, 응집도는 높다고 볼 수 있습니다.

## TODO
- [ ] Unit/Integration Test Code based on Spock Framework(BDD)
- [ ] Security, Oauth2 Impl
- [ ] CI/CD 구축해보기(travisCI?) -> Gradle에서 Unit/Integration Test Build 나뉘도록 구성해볼것.(Need search)
