var Generator = require('yeoman-generator');

module.exports = class extends Generator {
    constructor(args, opts) {
        // ensure generator is correctly configured
        super(args, opts);

        // Add custom code here
        // this.option('');
    }

    prompting() {
        const prompts = [
            {
                type: 'input',
                name: 'projectName',
                message: '(1/3) What is the name of your project?',
                default: this.appname
            },
            {
                type: 'list',
                name: 'projectType',
                message: '(2/3) What type of project are you creating?',
                choices: ['Command', 'Query', 'Worker']
            },
            {
                type: 'input',
                name: 'packageName',
                message: '(3/3) What is your default package name?',
                default: 'com.example'
            }
        ];

        return this.prompt(prompts).then(answers => {
            this.packageName = answers.packageName;
            this.projectName = answers.projectName;
            this.projectType = answers.projectType
        })
    }

    writing() {
        this.packageFolder = this.packageName.replace(/\./g, '/');
        this._generateBase();
    }

    _generateBase() {
        const kotlinDir = 'src/main/kotlin/' + this.packageFolder + '/';
        const kotlinDirTmpl = 'base/src/main/kotlin/package/';

        // Config files
        this.fs.copyTpl(
            this.templatePath('base/*.*'),
            this.destinationPath(),
            {
                packageName: this.packageName,
                projectName: this.projectName
            }
        )

        // Sources
        this.fs.copyTpl(
            this.templatePath(kotlinDirTmpl),
            this.destinationPath(kotlinDir),
            {
                packageName: this.packageName,
                projectName: this.projectName
            }
        )
    }
};