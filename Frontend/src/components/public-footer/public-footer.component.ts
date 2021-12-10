import { Component, OnInit } from "@angular/core";
import { faEnvelope } from "@fortawesome/free-solid-svg-icons";
import { faGithub } from "@fortawesome/free-brands-svg-icons";
import { faLinkedin } from "@fortawesome/free-brands-svg-icons";

@Component({
  selector: "app-public-footer",
  templateUrl: "./public-footer.component.html",
  styleUrls: ["./public-footer.component.css"],
})
export class PublicFooterComponent {
  faEnvelope = faEnvelope;
  faGithub = faGithub;
  faLinkedin = faLinkedin;
}

/* 
export class PublicFooterComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  } 

}
 */
