---
title: deeplearning---딥러닝
date: 2026-04-27 10:00:00 +0900
categories: [data, framework]
tags: [data, tensorflow, sklearn]
---

# DeepLearning - 딥러닝
---
**핵심**
- tensorflow.keras이라는 모듈 활용


---
## 데이터 전처리
1. 가변수화
```
# 라벨링
from sklearn.preprocessing import LabelEncoder
encoder = LabelEncoder()
y2 = encoder.fit_transform(y)


# 힛인코딩
from sklearn.keras.utils import to_categorical
y3 = to_categorical(y, 갯수)
```


2. 데이터 분리
```
from sklearn.model_selection import train_test_splict

x_trian, x_val, y_train, y_val = train_test_splict(x,y,train_size,random_state)
```


3. 스케일링
```
# 최대최소 스케일링
from sklearn.preprocessing import MinMaxScaler

# 기준 스케일링
from sklearn.preprocessing import StandScaler 
```


## 모델링 
1. 설치하기
- Sequential : 모델
- Input, Dense : 인풋과 히든및출력
- clear_session : 세션 클리어
- dropout : 최적화를 위해 다음층 가중치 양을 조절
```
from tensorflow.kears.models import Sequential
from tensorflow.kears.layers import Input, Dense, DropOut
from tensorflow.kears.backend import clear_session
```


2. 선언하기
- ativition : 활성화 함수로 히든층, 출력층마다 다름
    - 히든층 : 'relu', 'selu'
    - 츨력층 : 'linear'(회귀), 'sigmoid'(분류), 'argmax'(다중분류) 
```
model = Sequential([
    Input(shape=(x,)),
    # dropout(0.1) 최적화를 위해 다음층으로 가는 가중치 일부를 덜어낸다는 의미
    Dense(node, activition)
])
```


3. 학습하기
- 학습 설정
    - optimizers : 가중치 최적화 모듈
    - loss : 최적화 하는 기준인 오차
- 학습최적화
    - EarlyStopping : 일정값보다 떨어졌을 때 몇번 참는 후 일찍 끝내는 부분
    - monitor : 모니터링 할 값
    - min_delta : 변화량 최소 기준
    - patience : 몇번 참을지
- 학습하기
    - epochs : 몇번할까
    - validation_split : 검증자료는 전체의 몇퍼센트로 할까
    - callbacks : 학습최적화 변수
```
# 학습을 위한 설치 모듈
from tensorflow.kears.optimizers import Optimizer
from tensorflow.kears.metric import Metric
from tnesorflow.kears.losses import Loss


# 설정
model.compile(optimizers, loss, metric)

# 최적화 모듈
from tensorflow.keras.callbacks import EarlyStopping 
es = EarlyStopping(monitor, min_delta, patience)

# 학습
model.fit(x,y,epochs, batch_size, callback=[es], validation_data=(X_valid, y_valid))
```


4. 예측하기
```
y_pred = model.predict(x_val)
```


5. 평가하기
```
# 회귀형
## 절대값과 오차
from sklearn.metrics import mean_absoulte_error
## 절대값과 오차 제곱
from sklearn.metrics import mean_squared_error
## 절대값과 오차 퍼센트
from sklearn.metrics import mean_absoulte_percentage_error
## r2 점수
from sklearn.metrics import r2_score

# 분류형
## f1 점수
from sklearn.metrics import f1_socore
## 리콜, 정확도 정도
from sklearn.metrics import confusion_matrix
## f1, 리콜, 정확도 종합 정도
from sklearn.metrics import classification_report

# 평가
평가(y_val, y_pred)
```