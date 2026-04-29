---
title: finetuning---파인튜닝
date: 2026-04-27 10:00:00 +0900
categories: [genai]
tags: [ai, transformer]
---

# Fine_tuning - 파인튜닝
- 사전 훈련된 모델을 특정 작업이나 데이터셋에 맞게 미세 조정하는 과정
- 해당 문서는 **언어모델 파인튜닝 과정**을 담음

---
## 환경준비
**1. GPU 설정**
```
# gpu 임포트
import torch

# 설정
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
```


---
## 데이터 준비
**1. 데이터 로딩**
```
# 데이터 분석용 임포트
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

# 데이터 로딩
data = pd.read_csv

# 원하는 라벨를 분류하기 위한 라벨링
label_list = []
```


**2. 데이터 프레임 분할**
```
# 데이터 프레임 분할 임포트
from sklearn.model_selection import train_test_split
train, val = train_test_split(data, test_size=0.2, random_state=42)
```


**2. 각 데이터프레임으로부터 텐서 데이터셋 만들기**
```
# 데이터셋 임포트
from datasets import Dataset

# 데이터셋 만들기
train_ts = Dataset.from_pandas(train)
val_ts = Dataset.from_pandas(val)
```


**3. 모델 이름 할당과 토크나이즈**
> **AutoTokenizer.from_pretrained**
```
# 토그나이저 임포트
from transformers import AutoTokenizer

# 모델명 할당
model_name = "distilbert-base-uncased"
# 토크나이저 부르기
tokenizer = AutoTokenizer.from_pretrained(model_name)

# 토큰화 함수 생성 및 토큰화하기
def preprocess_function(data):
    return tokenizer(토큰화할 문장, truncation=길면짜를까, padding=길이같게할까)

train_ts = train_ts.map(preprocess_function, batched=여러데이터?)
val_ts = val_ts.map(preprocess_function, batched=여러데이터?)
```


---
## 파인튜닝
**1. 앞에서 토크나이징 한 사전학습 모델 준비**
> **AutoModelForSequenceClassification.from_pretrained**
```
# 사전학습 모델 임포트
from transformers import AutoModelForSequenceClassification 

# 모델 분류 출력갯수 설정 
n = 6
# 모델할당
model = AutoModelForSequenceClassification.from_pretrained(m,
                                                           num_labels = n).to(device)
```


**2. 학습 설정**
```
# 학습설정, 학습자 모듈 임포트
from transformers import TrainingArguments, Trainer, EarlyStoppingCallback

# 하이퍼파라미터
training_args = TrainingArguments(
    output_dir = ,           # 출력 형태
    eval_strategy = ,            # 평가할 전략
    save_strategy = ,            # 저장 전략
    learning_rate = ,               # 작은 학습률
    per_device_train_batch_size = ,   # 학습 배치 사이즈
    per_device_eval_batch_size = ,    # 평가 배치 사이즈
    num_train_epochs = ,               # 에폭 수
    weight_decay = ,                # weight decay
    load_best_model_at_end = ,      # earlystopping 사용하기 위해 필요(부울)
    logging_dir =,              # 로깅 저장 위치
    logging_steps = ,                 # 기록 간격
    report_to=             # 로그 시각화
)

# 학습자
trainer = Trainer(
    model=,                  # 학습할 모델
    args=,                  # TrainingArguments
    train_dataset = ,      # 트레이닝
    eval_dataset = ,       # 평가
    tokenizer = ,         # 토크나이저
    callbacks =          # 조기 종료
)
```


**3. 모델 학습**
```
trainer.train()
```


**4. 모델평가**
```
eval_results = trainer.evaluate()
```


**5. 모델 사용**
- text : 텍스트
- model : 모델
- tokenizer : 토크나이저
```
    # 모델을 CPU로 이동
    model = model.to("cpu")

    # 입력 문장 토크나이징 → CPU 텐서로 생성
    inputs = tokenizer(text, return_tensors=, truncation=, padding=)

    # 모델 예측 (no_grad로 메모리 절약)
    with torch.no_grad():
        outputs = model(**inputs)

    # 확률 계산
    logits = outputs.logits
    probabilities = torch.softmax(logits, dim=)

    # 예측 클래스
    pred = torch.argmax(probabilities, dim=).item()

    # 예측된 클래스 출력
    print(predicted_class)
    # 예측된 클래스 이름 출력
    print(label_list[predicted_class])
    # 확률 출력
    print(probabilities)
```


**6. 모델 검증 평가**
- val_ds : 텍스트
- model : 모델
- tokenizer : 토크나이저
```
    # 입력 데이터셋 토크나이징 (attention_mask 포함)
    inputs = tokenizer(list(val_ds['text']), return_tensors="pt", padding=True,
                       truncation=True, max_length=128
    )

    # 입력 텐서를 동일한 디바이스로 이동
    inputs = {key: value.to(device) for key, value in inputs.items()}  

    # 모델을 지정된 디바이스로 이동
    model = model.to(device)

    # 평가 과정에서 기울기 계산 비활성화
    with torch.no_grad():  
        outputs = model(**inputs)  # attention_mask를 포함해 입력

    # 예측 및 확률 계산
    probabilities = outputs.logits.softmax(dim=)

    # probabilities가 GPU에 있을 경우에만 CPU로 이동
    if probabilities.is_cuda:
        probabilities = probabilities.cpu().detach().numpy()
    else:
        probabilities = probabilities.detach().numpy()

    pred = np.argmax(probabilities, axis=)

    # GPU 메모리에서 필요 없는 텐서 제거 및 캐시 정리
    del inputs
    torch.cuda.empty_cache()

```


---
## 허깅 페이스에 모델 등록
**1. 모델 저장하기**
웹사이트를 통해 파일을 Hub로 업로드 : 모델 저장소를 만들고, 파일 업로드


**2. 허깅페이스에 모델 사용**
```
# pipeline 함수로 다운로드
from transformers import pipeline
emotion_classifier = pipeline(task, model )

# 모델 사용
emotion_classifier('I am really happy to see you again!')
```


---
## LoRA
**1. 라이브러리 로딩**
```
from peft import get_peft_model, LoraConfig, TaskType
```


**2. 사전학습 모델 준비**
```
# 기본 설정
model_name = "distilbert-base-uncased"
num_labels = 6
tokenizer = AutoTokenizer.from_pretrained(model_name)

# 사전 학습된 모델 로드 (전체 파라미터는 그대로)
base_model = AutoModelForSequenceClassification.from_pretrained(model_name, num_labels=num_labels)
```


**3. LoRA 구성**
```
# LoRA 구성 정의
lora_config = LoraConfig(
    task_type=,  # 시퀀스 분류
    r=,                         # 랭크 값 (작을수록 파라미터 적음)
    lora_alpha=,               # 너무 작으면 학습 효과 약하고, 너무 크면 발산 위험
    lora_dropout=,              # 드랍정도
    target_modules=,  # DistilBERT에서 사용되는 attention 레이어 이름
    bias=                 # bias
)

# LoRA 적용 (일부 레이어만 학습)
model = get_peft_model(base_model, lora_config)
```


**4. 학습**
```
# 학습설정, 학습자 모듈 임포트
from transformers import TrainingArguments, Trainer, EarlyStoppingCallback

training_args = TrainingArguments(
    output_dir = ,           # 출력 형태
    eval_strategy = ,            # 평가할 전략
    save_strategy = ,            # 저장 전략
    learning_rate = ,               # 작은 학습률
    per_device_train_batch_size = ,   # 학습 배치 사이즈
    per_device_eval_batch_size = ,    # 평가 배치 사이즈
    num_train_epochs = ,               # 에폭 수
    weight_decay = ,                # weight decay
    load_best_model_at_end = ,      # earlystopping 사용하기 위해 필요(부울)
    logging_dir =,              # 로깅 저장 위치
    logging_steps = ,                 # 기록 간격
    report_to=             # 로그 시각화
)

trainer = Trainer(
    model=,                  # 학습할 모델
    args=,                  # TrainingArguments
    train_dataset = ,      # 트레이닝
    eval_dataset = ,       # 평가
    tokenizer = ,         # 토크나이저
    callbacks =          # 조기 종료
)

trainer.train()
```


**5. 모델 사용**
- text : 텍스트
- model : 모델
- tokenizer : 토크나이저
```
    # 모델을 CPU로 이동
    model = model.to("cpu")

    # 입력 문장 토크나이징 → CPU 텐서로 생성
    inputs = tokenizer(text, return_tensors=, truncation=, padding=)

    # 모델 예측 (no_grad로 메모리 절약)
    with torch.no_grad():
        outputs = model(**inputs)

    # 확률 계산
    logits = outputs.logits
    probabilities = torch.softmax(logits, dim=)

    # 예측 클래스
    pred = torch.argmax(probabilities, dim=).item()

    # 예측된 클래스 출력
    print(predicted_class)
    # 예측된 클래스 이름 출력
    print(label_list[predicted_class])
    # 확률 출력
    print(probabilities)
```


**6. 모델 검증평가**
- val_ds : 텍스트
- model : 모델
- tokenizer : 토크나이저
```
    # 입력 데이터셋 토크나이징 (attention_mask 포함)
    inputs = tokenizer(list(val_ds['text']), return_tensors="pt", padding=True,
                       truncation=True, max_length=128
    )

    # 입력 텐서를 동일한 디바이스로 이동
    inputs = {key: value.to(device) for key, value in inputs.items()}  

    # 모델을 지정된 디바이스로 이동
    model = model.to(device)

    # 평가 과정에서 기울기 계산 비활성화
    with torch.no_grad():  
        outputs = model(**inputs)  # attention_mask를 포함해 입력

    # 예측 및 확률 계산
    probabilities = outputs.logits.softmax(dim=)

    # probabilities가 GPU에 있을 경우에만 CPU로 이동
    if probabilities.is_cuda:
        probabilities = probabilities.cpu().detach().numpy()
    else:
        probabilities = probabilities.detach().numpy()

    pred = np.argmax(probabilities, axis=)

    # GPU 메모리에서 필요 없는 텐서 제거 및 캐시 정리
    del inputs
    torch.cuda.empty_cache()

```
