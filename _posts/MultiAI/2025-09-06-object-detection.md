---
title: object-detection
date: 2026-04-27 10:00:00 +0900
categories: [ImageAI, theory]
tags: [multiai, theory]
---

# Object Detection
## Object Detection 개념
### Image Classification
- 이미지 전체에 대해 하나의 클래스를 예측하는 작업
    > 싱글 오브젝트 구분, 중심 클래스를 찾는 것

### Image Classification + Localization
- 이미지에서 단일 객체의 위치를 찾기 위해 경계박스 좌표를 예측
    > 싱글 중심 오브젝트의 위치까지 구분

### Object Detection
- 이미지 속 여러 개의 객체를 찾아내고 경계 박스 좌표와 각 객체의 클래스를 예측하느 작업
    > 멀티 오브젝트들을 구분하고, 위치까지 구분
    **R-CNN, YOLO 모델이 사용**

### Instatnce Segmentation
- 각 객체별로 픽셀 단위 영역을 정확히 구분(동일한 클래스라도 개별 객체를 따로 구별)
    > 멀티 오브젝트들의 각 개체들도 구분

---
## YOLO
- 이미지를 한 번만 보는 방식
- CNN 기반 회귀 모델로 Bounding Box와 클래스 예측을 한 번에 수행
- 전체 이미지를 한 번에 처리 -> 속도가 매우 빠름

> **핵심 아이디어**
> - 이미지를 여러개의 Grid Cell로 나눔
> - 각 Grid Cell에서 Bounding Box와 클래스 예측
> - 네트워크 한 번의 Forward Pass로 모든 객체 탐지

---
## YOLO 학습원리
1. 입력 이미지 + s * s Grid
    - 각 Grid Cell이 자신의 영역 내에서 객체를 탐지하고, 객체의 중심이 속한 Grid Cell이 그 객체를 예측

2. Bounding Box + Confidence Score 예측 
    - Bounding Box (x, y, w, h, confidence)
        > x, y : 객체 중심의 상대적 좌표
        > w, h : Bounding box의 너비와 높이
        > confidence : 박스 안에 객체가 있을 확률 * 박스의 정확성

3. 최종 예측
    - 각 Grid Cell이 예측한 b개의 bounding box 중 가장 신뢰도 높은 것만 선택
    - non maximum suppression 적용하여 중복된 박스를 제거
    - 최종적으로 정확한 Bounding Box와 클래스가 남음
    - 예제에서 개, 자전거, 자동차 등의 객체가 올바르게 탐지됨 

**non maximum suppression 방법**
1. Bounding box를 만든다
2. confidence score로 찾는 물체가 맞는지 평가하고 매긴다
3. IoU로 하나의 객체를 여러개로 구분했는지 평가하고 매긴다

**IoU**
    - Bounding box가 정확한가

**confidence Score**
    - Bounding Box 안에 object가 존재하는가?

---
## YOLO 모델 평가
**AP** 
    - 커브 아래 면적, 임계 값과 상관 없이 모델을 평가, 값이 높을 수록 좋은 모델

**mAP**
    - 각 클래스의 AP의 평균

**precisiion**
    - 정확도, 예측한 것들 중에서 실제 맞는 것 (오탐 줄이면 높아짐)

**recall**
    - 재현도, 실제있는 것들을 예측해서 찾은 것 (미탐 줄이면 높아짐)


---
## Instance Segmentaion
- 객체를 픽셀 단위로 분리 + 동일 클래스를 개별 객체를 구분

## YOLO 모델로 세그먼테이션을 수행
- object detection + instance segmentaion
    박스로 객체탐지
    클래스로 객체 구분
    픽셀 마스크로 인스턴스 별 픽셀 단위 구분
