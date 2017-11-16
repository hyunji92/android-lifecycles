/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.lifecycles.step2;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.Chronometer;

import com.example.android.codelabs.lifecycle.R;

public class ChronoActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** ViewModelStore는 새로운 ViewModel 또는 이전에 작성된 ViewModel을 제공합니다. */
        ChronometerViewModel chronometerViewModel
                = ViewModelProviders.of(this).get(ChronometerViewModel.class);


        /**매트로놈의 reference를 가져옵니다.*/
        Chronometer chronometer = findViewById(R.id.chronometer);

        if (chronometerViewModel.getStartTime() == null) {
            /** 시작 날짜가 정의되어 있지 않았다면 새로운 ViewModel이므로, ViewModel을 설정하세요. */
            long startTime = SystemClock.elapsedRealtime();
            chronometerViewModel.setStartTime(startTime);
            chronometer.setBase(startTime);
        } else {
            /**기존의 ViewModel이 있다면 ViewModel이 유지되고, 매트로놈 기준을 startTime으로 설정합니다.*/
            chronometer.setBase(chronometerViewModel.getStartTime());
        }

        chronometer.start();
    }
}
