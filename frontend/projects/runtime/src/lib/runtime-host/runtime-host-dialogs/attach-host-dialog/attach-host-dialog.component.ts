import {Component, Inject} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

export interface AttachHostDialogData {
  title: string;
  initialId: string;
}

@Component({
  selector: 'lib-attach-host-dialog',
  templateUrl: './attach-host-dialog.component.html'
})
export class AttachHostDialogComponent {

  hostForm: FormGroup;

  constructor(public dialogRef: MatDialogRef<AttachHostDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: AttachHostDialogData,
              private fb: FormBuilder) {
    this.hostForm = this.fb.group({
      hostId: [data.initialId, [
        Validators.required,
        Validators.maxLength(63),
        Validators.minLength(4),
        Validators.pattern(/^[a-z0-9]+[a-z0-9\-]*[a-z0-9]+$/),
      ]],
    });
  }

  get hostId() {
    return this.hostForm.get('hostId');
  }

  attach() {
    this.dialogRef.close(this.hostId.value);
  }

}
