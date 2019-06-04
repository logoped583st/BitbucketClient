/*
 * Copyright (C) 2014 Vlad Mihalachi
 *
 * This file is part of Turbo Editor.
 *
 * Turbo Editor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Turbo Editor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package sharedcode.turboeditor.preferences;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import sharedcode.turboeditor.R;
import sharedcode.turboeditor.activity.MainActivity;
import sharedcode.turboeditor.dialogfragment.EncodingDialog;
import sharedcode.turboeditor.dialogfragment.NumberPickerDialog;
import sharedcode.turboeditor.dialogfragment.ThemeDialog;
import sharedcode.turboeditor.util.ViewUtils;

public class SettingsFragment extends Fragment implements NumberPickerDialog.INumberPickerDialog, EncodingDialog.DialogListener, ThemeDialog.DialogListener {

    // Editor Variables
    private boolean sLineNumbers;
    private boolean sColorSyntax;
    private boolean sWrapContent;
    private boolean sUseMonospace;
    private boolean sReadOnly;
    private boolean sAccessoryView;
    private boolean sSuggestions;
    private boolean sSplitText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = getActivity();
        sUseMonospace = PreferenceHelper.getUseMonospace(context);
        sColorSyntax = PreferenceHelper.getSyntaxHighlight(context);
        sWrapContent = PreferenceHelper.getWrapContent(context);
        sLineNumbers = PreferenceHelper.getLineNumbers(context);
        sReadOnly = PreferenceHelper.getReadOnly(context);
        sAccessoryView = PreferenceHelper.getUseAccessoryView(context);
        sSuggestions = PreferenceHelper.getSuggestionActive(context);
        sSplitText = PreferenceHelper.getSplitText(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Our custom layout
        final View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        final SwitchCompat swLineNumbers, swSyntax, swWrapContent, swMonospace, swReadOnly;
        final SwitchCompat swSuggestions, swAccessoryView, swAutoSave, swIgnoreBackButton, swSplitText, swErrorReports;
        
        swLineNumbers = rootView.findViewById(R.id.switch_line_numbers);
        swSyntax = rootView.findViewById(R.id.switch_syntax);
        swWrapContent = rootView.findViewById(R.id.switch_wrap_content);
        swMonospace = rootView.findViewById(R.id.switch_monospace);
        swReadOnly = rootView.findViewById(R.id.switch_read_only);

        swSuggestions = rootView.findViewById(R.id.switch_suggestions_active);
        swAccessoryView = rootView.findViewById(R.id.switch_accessory_view);
        swSplitText = rootView.findViewById(R.id.switch_page_system);

        swLineNumbers.setChecked(sLineNumbers);
        swSyntax.setChecked(sColorSyntax);
        swWrapContent.setChecked(sWrapContent);
        swMonospace.setChecked(sUseMonospace);
        swReadOnly.setChecked(sReadOnly);

        swSuggestions.setChecked(sSuggestions);
        swAccessoryView.setChecked(sAccessoryView);
        swSplitText.setChecked(sSplitText);

        TextView fontSizeView, encodingView, extraOptionsView, themeView, goPro;
        fontSizeView = rootView.findViewById(R.id.drawer_button_font_size);
        encodingView = rootView.findViewById(R.id.drawer_button_encoding);
        extraOptionsView = rootView.findViewById(R.id.drawer_button_extra_options);
        themeView = rootView.findViewById(R.id.drawer_button_theme);



        swLineNumbers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                PreferenceHelper.setLineNumbers(getActivity(), isChecked);
                ((MainActivity) getActivity()).aPreferenceValueWasChanged(PreferenceChangeType.LINE_NUMERS);
            }
        });

        swSyntax.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sColorSyntax = isChecked;
                PreferenceHelper.setSyntaxHighlight(getActivity(), isChecked);
                ((MainActivity) getActivity()).aPreferenceValueWasChanged(PreferenceChangeType.SYNTAX);

            }
        });

        swWrapContent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                PreferenceHelper.setWrapContent(getActivity(), isChecked);
                ((MainActivity) getActivity()).aPreferenceValueWasChanged(PreferenceChangeType.WRAP_CONTENT);
            }
        });

        swMonospace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sUseMonospace = isChecked;
                PreferenceHelper.setUseMonospace(getActivity(), isChecked);
                ((MainActivity) getActivity()).aPreferenceValueWasChanged(PreferenceChangeType.MONOSPACE);

            }
        });

        swReadOnly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                PreferenceHelper.setReadOnly(getActivity(), isChecked);
                ((MainActivity) getActivity()).aPreferenceValueWasChanged(PreferenceChangeType.READ_ONLY);
            }
        });

        fontSizeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int fontMax = 36;
                int fontCurrent = //(int) (mEditor.getTextSize() / scaledDensity);
                        //fontMax / 2;
                        PreferenceHelper.getFontSize(getActivity());
                NumberPickerDialog dialogFrag = NumberPickerDialog.newInstance(NumberPickerDialog
                        .Actions
                        .FontSize, 1, fontCurrent, fontMax);
                dialogFrag.setTargetFragment(SettingsFragment.this, 0);
                dialogFrag.show(getFragmentManager().beginTransaction(), "dialog");
            }
        });

        encodingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EncodingDialog dialogFrag = EncodingDialog.newInstance();
                dialogFrag.setTargetFragment(SettingsFragment.this, 0);
                dialogFrag.show(getFragmentManager().beginTransaction(), "dialog");
            }
        });

        extraOptionsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View otherOptions = rootView.findViewById(R.id.other_options);
                boolean isVisible = otherOptions.getVisibility() == View.VISIBLE;
                ViewUtils.setVisible(otherOptions, !isVisible);
            }
        });

        themeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemeDialog dialogFrag = ThemeDialog.newInstance();
                dialogFrag.setTargetFragment(SettingsFragment.this, 0);
                dialogFrag.show(getFragmentManager().beginTransaction(), "dialog");
            }
        });

        swSuggestions.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                PreferenceHelper.setSuggestionsActive(getActivity(), isChecked);
                ((MainActivity) getActivity()).aPreferenceValueWasChanged(PreferenceChangeType.TEXT_SUGGESTIONS);
            }
        });

        swAccessoryView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                PreferenceHelper.setUseAccessoryView(getActivity(), isChecked);
                ((MainActivity) getActivity()).aPreferenceValueWasChanged(PreferenceChangeType.ACCESSORY_VIEW);
            }
        });


        swSplitText.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                PreferenceHelper.setSplitText(getActivity(), isChecked);
            }
        });


        return rootView;
    }

    @Override
    public void onNumberPickerDialogDismissed(NumberPickerDialog.Actions action, int value) {
        PreferenceHelper.setFontSize(getActivity(), value);
        ((MainActivity) getActivity()).aPreferenceValueWasChanged(PreferenceChangeType.FONT_SIZE);

    }

    @Override
    public void onEncodingSelected(String result) {
        PreferenceHelper.setEncoding(getActivity(), result);
        ((MainActivity) getActivity()).aPreferenceValueWasChanged(PreferenceChangeType.ENCODING);
    }

    @Override
    public void onThemeSelected(int result) {
        PreferenceHelper.setTheme(getActivity(), result);
        ((MainActivity) getActivity()).aPreferenceValueWasChanged(PreferenceChangeType.THEME_CHANGE);
    }
}
